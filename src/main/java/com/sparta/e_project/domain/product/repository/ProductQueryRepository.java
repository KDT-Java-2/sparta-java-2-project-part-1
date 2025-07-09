package com.sparta.e_project.domain.product.repository;


import static com.sparta.e_project.domain.category.entity.QCategory.category;
import static com.sparta.e_project.domain.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.e_project.domain.product.dto.ProductRequest;
import com.sparta.e_project.domain.product.dto.ProductResponse;
import com.sparta.e_project.domain.product.dto.ProductSearchResponse;
import com.sparta.e_project.domain.product.dto.QProductResponse;
import com.sparta.e_project.domain.product.dto.QProductSearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public ProductResponse findProductById(Long productId) {
    return jpaQueryFactory
        .select(new QProductResponse(
                product.id
                , product.name
                , product.description
                , product.price
                , product.stock
                , product.category.id
                , product.category.name
            )
        )
        .from(product)
        .where(product.id.eq(productId))
        .join(product.category, category)
        .fetchOne();
  }

  public Page<ProductSearchResponse> findPagedProducts(ProductRequest request, Pageable pageable) {
    List<ProductSearchResponse> productResponses = jpaQueryFactory
        .select(new QProductSearchResponse(
            product.id,
            product.name,
            product.price,
            product.stock))
        .from(product)
        .where(
            categoryEq(request.getCategory()),
            priceGoe(request.getMinPrice()),
            priceLoe(request.getMaxPrice())
        )
        // order by에는 필드명,정렬 방향이 올수 있다.
        .orderBy(createOrderSpecifiers(pageable.getSort()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    long total = jpaQueryFactory.select(product.count())
        .from(product)
        .where(
            categoryEq(request.getCategory()),
            priceGoe(request.getMinPrice()),
            priceLoe(request.getMaxPrice())
        )
        .fetchOne();

    return new PageImpl<>(productResponses, pageable, total);
  }

  private BooleanExpression categoryEq(Long categoryId) {
    return categoryId != null ? product.category.id.eq(categoryId) : null;
  }

  private BooleanExpression priceGoe(Integer minPrice) {
    return minPrice != null ? product.price.goe(minPrice) : null;
  }

  private BooleanExpression priceLoe(Integer maxPrice) {
    return maxPrice != null ? product.price.loe(maxPrice) : null;
  }

  private OrderSpecifier<?>[] createOrderSpecifiers(Sort sort) {
    return sort.stream()
        .map(order -> {
          Order direction = order.isAscending() ? Order.ASC : Order.DESC;
          String property = order.getProperty();

          switch (property) {
            case "name":
              return new OrderSpecifier<>(direction, product.name);
            case "price":
              return new OrderSpecifier<>(direction, product.price);
            case "stock":
              return new OrderSpecifier<>(direction, product.stock);
            case "createdAt":
              return new OrderSpecifier<>(direction, product.createdAt);
            default:
              return new OrderSpecifier<>(Order.ASC, product.id); // 기본 정렬
          }
        })
        .toArray(OrderSpecifier[]::new);
  }

}
