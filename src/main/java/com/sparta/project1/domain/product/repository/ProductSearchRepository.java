package com.sparta.project1.domain.product.repository;

import static com.sparta.project1.domain.product.entity.QProduct.product;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.project1.domain.product.dto.ProductSearchRequest;
import com.sparta.project1.domain.product.entity.Product;
import com.sparta.project1.domain.product.entity.QProduct;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductSearchRepository {

  private final JPAQueryFactory queryFactory;

  public Page<Product> searchProducts(Long category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
    JPAQuery<Product> query = queryFactory.selectFrom(product)
        .join(product.category)
        .where(
            eqCategoryId(category),
            goeMinPrice(minPrice),
            loeMaxPrice(maxPrice)
        );

    List<Product> content = query
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    //전체 갯수
    Long total = queryFactory.select(product.count())
        .where(
            eqCategoryId(category),
            goeMinPrice(minPrice),
            loeMaxPrice(maxPrice)
        ).fetchOne();

    return new PageImpl<>(content, pageable, total);
  }

  private BooleanExpression eqCategoryId(Long categoryId) {
    return categoryId != null ? QProduct.product.category.id.eq(categoryId) : null;
  }

  private BooleanExpression goeMinPrice(BigDecimal minPrice) {
    return minPrice != null ? QProduct.product.price.goe(minPrice) : null;
  }

  private BooleanExpression loeMaxPrice(BigDecimal maxPrice) {
    return maxPrice != null ? QProduct.product.price.loe(maxPrice) : null;
  }
}
