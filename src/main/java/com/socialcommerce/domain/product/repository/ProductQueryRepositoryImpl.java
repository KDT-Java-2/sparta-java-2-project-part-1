package com.socialcommerce.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.socialcommerce.domain.product.entity.Product;
import com.socialcommerce.domain.product.entity.QProduct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Product> searchProducts(
      Long category,
      Integer minPrice,
      Integer maxPrice,
      Pageable pageable
  ) {
    QProduct product = QProduct.product;

    // QueryDSL 동적 쿼리 예시
    List<Product> content = queryFactory
        .selectFrom(product)
        .where(
            category != null ? product.category.id.eq(category) : null,
            minPrice != null ? product.price.goe(minPrice) : null,
            maxPrice != null ? product.price.loe(maxPrice) : null
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(product.createdAt.desc())
        .fetch();

    // 전체 개수 카운트 쿼리
    Long total = queryFactory
        .select(product.count())
        .from(product)
        .where(
            category != null ? product.category.id.eq(category) : null,
            minPrice != null ? product.price.goe(minPrice) : null,
            maxPrice != null ? product.price.loe(maxPrice) : null
        )
        .fetchOne();

    return PageableExecutionUtils.getPage(content, pageable, () -> total);
  }
}
