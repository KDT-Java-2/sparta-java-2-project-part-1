package com.sparta.commerce.domain.product.repository;

import static com.sparta.commerce.domain.product.dto.QProductFindResponse.*;
import static com.sparta.commerce.domain.product.entity.QProduct.product;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.domain.product.dto.ProductFindResponse;
import com.sparta.commerce.domain.product.dto.QProductFindResponse;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.dto.ProductFindRequest;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public Page<ProductFindResponse> findSearchProducts(
      ProductFindRequest productFindRequest,
      Pageable pageable) {
    List<ProductFindResponse> products = jpaQueryFactory
        .select(new QProductFindResponse(
            product.id,
            product.name,
            product.price,
            product.stock
        ))
        .from(product)
        .where(
            categoryEq(productFindRequest.getCategoryId()),
            minPriceEq(productFindRequest.getMinPrice()),
            maxPriceEq(productFindRequest.getMaxPrice())
        )
        .offset(pageable.getOffset()) // offset은 페이지네이션을 위해 사용됨
        .limit(pageable.getPageSize()) // limit은 페이지당 최대 개수를 지정
        .orderBy(productSpecifier(productFindRequest.getSortBy())) // 정렬 기준
        .fetch();

    Long totalCount = jpaQueryFactory
        .select(product.count())
        .from(product)
        .where(
            categoryEq(productFindRequest.getCategoryId()),
            minPriceEq(productFindRequest.getMinPrice()),
            maxPriceEq(productFindRequest.getMaxPrice())
        )

        .fetchOne();

    return new PageImpl<>(products, pageable, totalCount != null ? totalCount : 0L);

  }

  private BooleanExpression categoryEq(Long categoryId) {
    return categoryId != null ? product.category.id.eq(categoryId) : null;
  }

  private BooleanExpression minPriceEq(BigDecimal minPrice) {
    return minPrice != null ? product.price.goe(minPrice) : null;
  }

  private BooleanExpression maxPriceEq(BigDecimal maxPrice) {
    return maxPrice != null ? product.price.loe(maxPrice) : null;
  }

  private OrderSpecifier productSpecifier(String sortBy) {
    if (sortBy == null || sortBy.isEmpty()) {
      return product.createdAt.desc(); // 기본 정렬은 생성일 기준 내림차순
    }

    return switch (sortBy) {
      case "price,asc" -> product.price.asc();
      case "price,desc" -> product.price.desc();
      case "name,asc" -> product.name.asc();
      case "name,desc" -> product.name.desc();
      case "createdAt,asc" -> product.createdAt.asc();
      // case "createdAt,desc" -> product.createdAt.desc();
      default -> product.createdAt.desc();
    };
  }



}
