package com.sparta.coupang_commerce.domain.product.repository;

import static com.sparta.coupang_commerce.domain.category.entity.QCategory.category;
import static com.sparta.coupang_commerce.domain.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.coupang_commerce.domain.product.entity.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  public Page<Product> findProducts(Long categoryId, Integer minPrice, Integer maxPrice, Pageable pageable) {
    List<Product> products = queryFactory.select(product)
        .distinct()// fetch join 시 중복 방지
        .from(product)
        .leftJoin(product.category, category)
        .fetchJoin()
        .where(
            priceGoe(minPrice),
            priceLoe(maxPrice))
        .orderBy(resolveOrderSpecifiers(pageable))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long total = queryFactory
        .select(product.count())
        .distinct()// fetch join 시 중복 방지
        .from(product)
        .leftJoin(product.category, category)
        .where(priceGoe(minPrice), priceLoe(maxPrice))
        .fetchOne();

    return new PageImpl<>(products, pageable, total);
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

  private OrderSpecifier<?>[] resolveOrderSpecifiers(Pageable pageable) {
    // PathBuilder 로 Product 엔티티 루트 생성
    PathBuilder<Product> productPath = new PathBuilder<>(Product.class, "product");

    if (pageable.getSort().isUnsorted()) {
      // 기본 정렬: createdAt desc
      return new OrderSpecifier<?>[]{product.createdAt.desc()};
    }

    return pageable.getSort().stream()
        .map(order -> {
          // 프로퍼티 이름
          String prop = order.getProperty();

          // ComparableExpressionBase 로 가져오기
          // (타입이 맞지 않으면 PathBuilder.getComparable 에서 예외 발생)
          ComparableExpressionBase<?> expr = productPath
              .getComparable(prop, Comparable.class);

          return new OrderSpecifier<>(
              order.isAscending() ? Order.ASC : Order.DESC,
              expr
          );
        })
        .toArray(OrderSpecifier[]::new);
  }

}
