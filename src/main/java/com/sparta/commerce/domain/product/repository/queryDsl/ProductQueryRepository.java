package com.sparta.commerce.domain.product.repository.queryDsl;

import static com.sparta.commerce.common.utils.QueryUtils.ifNotNull;
import static com.sparta.commerce.domain.product.entity.QProduct.product;
import static com.sparta.commerce.domain.product.entity.QProductItem.productItem;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.domain.product.dto.product.ProductSearchCondition;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.repository.ProductOrder;
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

  private final JPAQueryFactory queryFactory;

  public Page<Product> searchProducts(ProductSearchCondition searchCondition, Pageable pageable){
    BooleanBuilder builder = new BooleanBuilder();
    builder = priceBetween(builder, searchCondition.getMinPrice(), searchCondition.getMaxPrice())
        .and(containsKeyword(searchCondition.getKeyword()))
        .and(categoryEqual(searchCondition.getCategoryId()));

    ProductOrder order = searchCondition.getSort() != null ? searchCondition.getSort() : ProductOrder.LATEST;

    List<Product> products = queryFactory.select(product)
        .from(product)
        .join(productItem).on(productItem.product.eq(product))
        .where(builder)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(order.getOrderSpecifier())
        .fetch();

    long total = queryFactory.select(product.count())
        .from(product)
        .join(productItem).on(productItem.product.eq(product))
        .where(builder)
        .fetchOne();

    return new PageImpl<>(products, pageable, total);
  }

  private BooleanExpression categoryEqual(Long categoryId){
    return ifNotNull(categoryId, id -> product.category.id.eq(id));
  }

  private BooleanExpression containsKeyword(String keyword){
    return ifNotNull(keyword,
        k -> product.name.containsIgnoreCase(k)
            .or(product.brand.containsIgnoreCase(k))
    );
  }

  private BooleanBuilder priceBetween(BooleanBuilder builder, BigDecimal minPrice, BigDecimal maxPrice){
    builder.and(ifNotNull(minPrice, p -> product.basePrice.goe(p)));
    builder.and(ifNotNull(maxPrice, p -> product.basePrice.loe(p)));
    return builder;
  }
}
