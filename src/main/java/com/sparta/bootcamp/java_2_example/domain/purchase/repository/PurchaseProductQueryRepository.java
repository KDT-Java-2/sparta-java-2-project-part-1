package com.sparta.bootcamp.java_2_example.domain.purchase.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.bootcamp.java_2_example.common.enums.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.sparta.bootcamp.java_2_example.domain.purchase.entity.QPurchase.purchase;
import static com.sparta.bootcamp.java_2_example.domain.purchase.entity.QPurchaseProduct.purchaseProduct;

@Repository
@RequiredArgsConstructor
public class PurchaseProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean existsInCompletedOrders(Long productId) {
        return queryFactory
                .selectOne()
                .from(purchaseProduct)
                .join(purchase)
                .on(purchase.id.eq(purchaseProduct.product.id))
                .where(
                        purchaseProduct.purchase.id.eq(productId),
                        purchase.status.eq(PurchaseStatus.COMPLETED)
                )
                .fetchFirst() != null;
    }

}
