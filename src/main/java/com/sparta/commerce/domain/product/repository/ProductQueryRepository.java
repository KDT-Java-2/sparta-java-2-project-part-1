package com.sparta.commerce.domain.product.repository;

import static com.sparta.commerce.domain.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.domain.product.dto.ProductSearchRequest;
import com.sparta.commerce.domain.product.dto.ProductSearchResponse;
import com.sparta.commerce.domain.product.dto.QProductSearchResponse;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  public Page<ProductSearchResponse> searchAll(ProductSearchRequest productSearchRequest, Pageable pageable) {
    JPAQuery<ProductSearchResponse> query = queryFactory
        .select(
            new QProductSearchResponse(
                product.id,
                product.name,
                product.category.id,
                product.price,
                product.stock,
                product.createdAt
            ))
        .from(product)
        .where(
            categoryEq(productSearchRequest.categoryId()),
            priceGoe(productSearchRequest.minPrice()),
            priceLoe(productSearchRequest.maxPrice())
        );

    // QueryDSL order
    PathBuilder<Object> pathBuilder = new PathBuilder<>(Object.class, "product");
    for (Sort.Order order : pageable.getSort()) {
      query.orderBy(new OrderSpecifier(
          order.isAscending() ? Order.ASC : Order.DESC,
          pathBuilder.get(order.getProperty())
      ));
    }

    List<ProductSearchResponse> products = query
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long totalCount = queryFactory
        .select(product.count())
        .from(product)
        .where(
            categoryEq(productSearchRequest.categoryId()),
            priceGoe(productSearchRequest.minPrice()),
            priceLoe(productSearchRequest.maxPrice())
        )
        .fetchOne();

    return new PageImpl<>(products, pageable, totalCount);
  }

  private BooleanExpression categoryEq(Long categoryId) {
    return categoryId != null ? product.category.id.eq(categoryId) : null;
  }

  private BooleanExpression priceGoe(Integer minPrice) {
    return minPrice != null ? product.price.goe(BigDecimal.valueOf(minPrice)) : null;
  }

  private BooleanExpression priceLoe(Integer maxPrice) {
    return maxPrice != null ? product.price.loe(BigDecimal.valueOf(maxPrice)) : null;
  }

}
