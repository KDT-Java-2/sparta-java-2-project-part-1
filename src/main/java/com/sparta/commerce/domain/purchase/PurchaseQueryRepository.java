package com.sparta.commerce.domain.purchase;

import static com.sparta.commerce.domain.purchase.entity.QPurchaseProduct.purchaseProduct;
import static com.sparta.commerce.domain.purchase.entity.QPurchase.purchase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.common.enums.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PurchaseQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public Boolean existsCompletedPurchaseProductForProduct(Long productId, PurchaseStatus purchaseStatus) {
    return jpaQueryFactory
        .selectOne()
        .from(purchaseProduct)
        .join(purchaseProduct.purchase, purchase)
        .where(purchaseProduct.product.id.eq(productId)
//            .and(purchase.status.eq(purchaseStatus))
        )
        .fetchFirst() != null;
  }

}
