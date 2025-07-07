package com.sparta.part_1.domain.purchase.repository;

import static com.sparta.part_1.domain.purchase.entity.QPurchaseProduct.purchaseProduct;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.part_1.common.enums.PurchaseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PurchaseQueryRepository {

  private final JPAQueryFactory queryFactory;


  public Long getCompletePurchaseCount(@NotNull Long productId) {
    return queryFactory.select(purchaseProduct.count())
        .from(purchaseProduct)
        .where(
            purchaseProduct.product.id.eq(productId),
            purchaseProduct.purchase.status.eq(PurchaseStatus.COMPLETION)
        )
        .fetchOne();
  }
}
