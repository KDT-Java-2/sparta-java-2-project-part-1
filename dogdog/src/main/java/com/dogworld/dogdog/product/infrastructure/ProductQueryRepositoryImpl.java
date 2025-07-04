package com.dogworld.dogdog.product.infrastructure;

import com.dogworld.dogdog.product.interfaces.dto.request.ProductSearchCondition;
import com.dogworld.dogdog.category.domain.QCategory;
import com.dogworld.dogdog.product.domain.Product;
import com.dogworld.dogdog.product.domain.QProduct;
import com.dogworld.dogdog.purchase.domain.PurchaseStatus;
import com.dogworld.dogdog.purchase.domain.QPurchase;
import com.dogworld.dogdog.purchaseproduct.domain.QPurchaseProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
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
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Product> search(ProductSearchCondition condition, Pageable pageable) {
    QProduct product = QProduct.product;
    QCategory category = QCategory.category;

    Predicate predicate = buildPredicate(condition);
    List<Product> content = getContent(pageable, product, category, predicate);
    Long total = getTotalCount(product, category, predicate);

    return new PageImpl<>(content, pageable, total != null ? total: 0L);
  }

  @Override
  public boolean isIncludeInCompletedOrder(Long productId) {
    QPurchase purchase = QPurchase.purchase;
    QPurchaseProduct purchaseProduct = QPurchaseProduct.purchaseProduct;

    return queryFactory
        .selectOne()
        .from(purchaseProduct)
        .join(purchaseProduct.purchase, purchase)
        .where(
            purchaseProduct.product.id.eq(productId),
            purchase.status.eq(PurchaseStatus.COMPLETED)
        )
        .fetchFirst() != null;
  }

  private Predicate buildPredicate(ProductSearchCondition condition) {
    return new BooleanBuilder()
        .and(categoryEq(condition.getCategory()))
        .and(priceGoe(condition.getMinPrice()))
        .and(priceLoe(condition.getMaxPrice()));
  }

  private List<Product> getContent(Pageable pageable, QProduct product, QCategory category,
      Predicate predicate) {
    return queryFactory
        .selectFrom(product)
        .join(product.category, category)
        .fetchJoin()
        .where(predicate)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(getOrderSpecifier(pageable, product))
        .fetch();
  }

  private Long getTotalCount(QProduct product, QCategory category, Predicate predicate) {
    return queryFactory
        .select(product.count())
        .from(product)
        .join(product.category, category)
        .where(predicate)
        .fetchOne();
  }

  private BooleanExpression categoryEq(Long categoryId) {
    return categoryId != null ? QProduct.product.category.id.eq(categoryId) : null;
  }

  private BooleanExpression priceGoe(Integer price) {
    return price != null ? QProduct.product.price.goe(BigDecimal.valueOf(price)) : null;
  }

  private BooleanExpression priceLoe(Integer price) {
    return price != null ? QProduct.product.price.loe(BigDecimal.valueOf(price)) : null;
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
