package com.dogworld.dogdog.domain.product.repository;

import com.dogworld.dogdog.api.product.request.ProductSearchCondition;
import com.dogworld.dogdog.domain.category.QCategory;
import com.dogworld.dogdog.domain.product.Product;
import com.dogworld.dogdog.domain.product.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepositoryImpl implements ProductQueryRepository{

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Product> search(ProductSearchCondition condition, Pageable pageable) {
    QProduct product = QProduct.product;
    QCategory category = QCategory.category;

    BooleanBuilder builder = new BooleanBuilder();

    if(condition.getCategory() != null) {
      builder.and(product.category.id.eq(condition.getCategory()));
    }

    if(condition.getMinPrice() != null) {
      builder.and(product.price.goe(BigDecimal.valueOf(condition.getMinPrice())));
    }

    if(condition.getMaxPrice() != null) {
      builder.and(product.price.loe(BigDecimal.valueOf(condition.getMaxPrice())));
    }

    List<Product> content = queryFactory
        .selectFrom(product)
        .join(product.category, category)
        .fetchJoin()
        .where(builder)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(getOrderSpecifier(pageable, product))
        .fetch();


    Long total = queryFactory
        .select(product.count())
        .from(product)
        .join(product.category, category)
        .where(builder)
        .fetchOne();

    System.out.println("builder: " + builder.toString());

    return new PageImpl<>(content, pageable, total != null ? total: 0L);
  }

  private OrderSpecifier<?> getOrderSpecifier(Pageable pageable, QProduct product) {
    if(pageable.getSort().isEmpty()) {
      return product.createdAt.desc();
    }

    Sort.Order order = pageable.getSort().iterator().next();
    PathBuilder<Product> pathBuilder = new PathBuilder<>(Product.class, "product");

    return order.isAscending()
        ? pathBuilder.getComparable(order.getProperty(), Comparable.class).asc()
        : pathBuilder.getComparable(order.getProperty(), Comparable.class).desc();
  }
}
