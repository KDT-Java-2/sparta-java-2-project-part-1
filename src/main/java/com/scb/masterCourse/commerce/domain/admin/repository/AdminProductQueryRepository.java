package com.scb.masterCourse.commerce.domain.admin.repository;


import static com.scb.masterCourse.commerce.domain.product.entity.QProduct.product;
import static com.scb.masterCourse.commerce.domain.purchase.entity.QPurchase.purchase;
import static com.scb.masterCourse.commerce.domain.purchase.entity.QPurchaseProduct.purchaseProduct;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scb.masterCourse.commerce.common.enums.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Integer countCompletedPurchasesByProductId(Long productId) {
        return queryFactory
            .select(purchase.count()
                .intValue())
            .from(purchaseProduct)
            .join(purchaseProduct.product, product)
            .join(purchaseProduct.purchase, purchase)
            .where(
                purchase.status.eq(PurchaseStatus.COMPLETED),
                product.id.eq(productId)
            )
            .fetchOne();
    }
}
