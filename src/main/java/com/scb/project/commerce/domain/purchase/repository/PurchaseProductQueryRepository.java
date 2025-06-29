package com.scb.project.commerce.domain.purchase.repository;

import static com.scb.project.commerce.domain.product.entity.QProduct.product;
import static com.scb.project.commerce.domain.purchase.entity.QPurchase.purchase;
import static com.scb.project.commerce.domain.purchase.entity.QPurchaseProduct.purchaseProduct;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scb.project.commerce.common.enums.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PurchaseProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * 특정 상품이 배송중 또는 배송완료 상태의 주문에 포함되었는지 확인합니다.
     *
     * <p>상품 삭제 전에 참조 무결성 제약을 검증하기 위해 사용됩니다.
     * <br>배송 상태가 SHIPPED 또는 DELIVERED인 경우 삭제 불가하도록 판단 기준이 됩니다.
     *
     * @param productId 확인할 상품 ID
     * @return true: 해당 상품이 배송중 또는 배송완료 상태의 주문에 포함됨, false: 포함되지 않음
     */
    public boolean existsCompletedProduct(Long productId) {
        return queryFactory.selectOne()
            .from(purchaseProduct)
            .join(purchaseProduct.product, product)
            .join(purchaseProduct.purchase, purchase)
            .where(
                productIdEq(productId),
                purchase.status.in(PurchaseStatus.DELIVERED, PurchaseStatus.SHIPPED)
            )
            .fetchFirst() != null;
    }


    // 상품 ID Null 체크
    private BooleanExpression productIdEq(Long productId) {
        return productId != null ? product.id.eq(productId) : null;
    }
}
