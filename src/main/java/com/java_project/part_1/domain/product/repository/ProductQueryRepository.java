package com.java_project.part_1.domain.product.repository;

import static com.java_project.part_1.domain.product.entity.QProduct.product;

import com.java_project.part_1.domain.product.entity.Product;
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

  private final JPAQueryFactory queryFactory;


  public Page<Product> findProducts(Long categoryId, Integer minPrice, Integer maxPrice,
      Pageable pageable) {
    List<Product> products = queryFactory
        .select(product)  // QProduct.product
        .from(product)
        .where(
            betweenPrice(minPrice, maxPrice),
            product.category.id.eq(categoryId)
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(product.createdAt.desc())
        .fetch();

    Long totalCount = queryFactory
        .select(product.count())
        .from(product)
        .where(
            betweenPrice(minPrice, maxPrice),
            product.category.id.eq(categoryId)
        )
        .fetchOne();

    return new PageImpl<>(products, pageable, totalCount);
  }

  private BooleanExpression betweenPrice(Integer minPrice, Integer maxPrice) {
    return product.price.between(minPrice, maxPrice);
  }
}
