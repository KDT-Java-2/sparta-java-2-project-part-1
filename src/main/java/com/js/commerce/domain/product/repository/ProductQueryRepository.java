package com.js.commerce.domain.product.repository;

import static com.js.commerce.domain.product.entity.QProduct.product;

import com.js.commerce.domain.product.dto.ProductSearchCondition;
import com.js.commerce.domain.product.entity.Product;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

  public Page<Product> search(ProductSearchCondition condition, Pageable pageable) {
    BooleanExpression predicate = categoryEq(condition.getCategoryId())
        .and(priceGoe(condition.getMinPrice()))
        .and(priceLoe(condition.getMaxPrice()));

    List<Product> content = jpaQueryFactory
        .selectFrom(product)
        .where(predicate)
        .orderBy(getOrderSpecifier(condition.getSortBy()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    long total = jpaQueryFactory
        .select(product.count())
        .from(product)
        .where(predicate)
        .fetchOne();

    return new PageImpl<>(content, pageable, total);
  }

  private BooleanExpression categoryEq(Long categoryId) {
    return categoryId != null
        ? product.category.id.eq(categoryId)
        : null;
  }

  private BooleanExpression priceGoe(Integer minPrice) {
    return minPrice != null
        ? product.price.goe(minPrice)
        : null;
  }

  private BooleanExpression priceLoe(Integer maxPrice) {
    return maxPrice != null
        ? product.price.loe(maxPrice)
        : null;
  }

  private OrderSpecifier<?> getOrderSpecifier(String sortBy) { // sortBy는 (필드명,정렬방식) 형식으로 요청
    // sortBy 파라미터가 없으면 최신 등록순 (createdAt,desc)으로 기본 정렬
    if (sortBy == null || sortBy.isBlank()) {
      return product.createdAt.desc();
    }
    // 예: (price,asc) 또는 (createdAt,desc)
    String[] parts = sortBy.split(",", 2); // 최대 2개만 분할
    String field;
    boolean asc;

    if (parts.length == 1) {
      // 쉼표가 없으면 parts[0]이 방향인지 필드인지 구분
      if (parts[0].equalsIgnoreCase("desc")) {
        field = "createdAt";
        asc = false;
      } else if (parts[0].equalsIgnoreCase("asc")) {
        field = "createdAt";
        asc = true;
      } else {
        field = parts[0];
        asc = true;  // 필드명만 있으면 기본 오름차순
      }
    } else {
      // parts.length == 2
      field = parts[0];
      asc = parts[1].equalsIgnoreCase("asc");
    }

    switch (field) {
      case "price":
        return asc ? product.price.asc() : product.price.desc();
      case "createdAt":
        return asc ? product.createdAt.asc() : product.createdAt.desc();
      // 필요에 따라 다른 필드도 추가
      default:
        return product.createdAt.desc();
    }
  }

}
