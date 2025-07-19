package com.sparta.ecommerce.domain.product.repository;

import static com.sparta.ecommerce.domain.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.ecommerce.domain.product.dto.ProductListResponse;
import com.sparta.ecommerce.domain.product.dto.ProductSearchRequest;
import com.sparta.ecommerce.domain.product.dto.QProductListResponse;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  public Page<ProductListResponse> findProductList(ProductSearchRequest request,
      Pageable pageable) {

    List<ProductListResponse> list = queryFactory
        .select(new QProductListResponse(
            product.id,
            product.productNm,
            product.price,
            product.stock
        ))
        .from(product)
        .where(
            categoryEq(request.getCategoryId()),
            priceGoe(request.getMinPrice()),
            priceLoe(request.getMaxPrice())
        )
        .orderBy(toOrderSpecifier(pageable.getSort()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long totalCount = queryFactory
        .select(product.count())
        .from(product)
        .where(
            categoryEq(request.getCategoryId()),
            priceGoe(request.getMinPrice()),
            priceLoe(request.getMaxPrice()),
            nameContains(request.getName()),
            descriptionContains(request.getDescription())
        )
        .fetchOne();

    return new PageImpl<>(list, pageable, totalCount != null ? totalCount : 0);
  }

  // 카테고리 필터
  private BooleanExpression categoryEq(Long categoryId) {
    return categoryId != null ? product.category.id.eq(categoryId) : null;
  }

  // 가격 필터
  private BooleanExpression priceGoe(BigDecimal minPrice) {
    return minPrice != null ? product.price.goe(minPrice) : null;
  }

  private BooleanExpression priceLoe(BigDecimal maxPrice) {
    return maxPrice != null ? product.price.loe(maxPrice) : null;
  }

  private BooleanExpression nameContains(String name) {
    return StringUtils.hasText(name) ? product.productNm.containsIgnoreCase(name) : null;
  }

  private BooleanExpression descriptionContains(String description) {
    return StringUtils.hasText(description) ? product.description.containsIgnoreCase(description)
        : null;
  }

  private OrderSpecifier<?> toOrderSpecifier(Sort sort) {
    for (Sort.Order order : sort) {
      switch (order.getProperty()) {
        case "price":
          return new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, product.price);
        case "createdAt":
          return new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC,
              product.createdAt);
        case "productNm":
          return new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC,
              product.productNm);
      }
    }
    // 기본 정렬: createdAt DESC
    return new OrderSpecifier<>(Order.DESC, product.createdAt);
  }
}