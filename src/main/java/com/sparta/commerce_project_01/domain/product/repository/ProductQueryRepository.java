package com.sparta.commerce_project_01.domain.product.repository;

import static com.sparta.commerce_project_01.domain.product.entity.QProduct.product;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce_project_01.domain.product.entity.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
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

  public Page<Product> findProducts(Long categoryId, Integer minPrice, Integer maxPrice,
      Pageable pageable) {

    BooleanBuilder builder = new BooleanBuilder();
    if (categoryId != null) {
      builder.and(product.category.id.eq(categoryId));
    }
    if (minPrice != null) {
      builder.and(product.price.goe(BigDecimal.valueOf(minPrice)));
    }
    if (maxPrice != null) {
      builder.and(product.price.loe(BigDecimal.valueOf(maxPrice)));
    }

    return new PageImpl<>(
        queryFactory
            .selectFrom(product)
            .leftJoin(product.category).fetchJoin()  // fetch join 추가
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(getOrderSpecifier(pageable))
            .fetch(),
        pageable,
        getTotalCount(builder)
    );

  }

  private OrderSpecifier<?>[] getOrderSpecifier(Pageable pageable) {
    if (!pageable.getSort().isEmpty()) {
      List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

      pageable.getSort().forEach(order -> {
        Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

        switch (order.getProperty()) {
          case "price":
            orderSpecifiers.add(new OrderSpecifier<>(direction, product.price));
            break;
          case "name":
            orderSpecifiers.add(new OrderSpecifier<>(direction, product.name));
            break;
          case "id":
            orderSpecifiers.add(new OrderSpecifier<>(direction, product.id));
            break;
          // 필요한 정렬 필드 추가
        }
      });

      return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

    // 기본 정렬: id 내림차순
    return new OrderSpecifier[]{new OrderSpecifier<>(Order.DESC, product.id)};
  }

  private long getTotalCount(BooleanBuilder builder) {

    return queryFactory
        .selectFrom(product)
        .where(builder)
        .fetchCount();
  }

  private BooleanExpression priceBetween(Integer minPrice, Integer maxPrice) {
    return product.price.between(minPrice, maxPrice);
  }
}