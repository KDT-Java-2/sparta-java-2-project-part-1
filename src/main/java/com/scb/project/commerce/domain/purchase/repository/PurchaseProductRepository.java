package com.scb.project.commerce.domain.purchase.repository;

import com.scb.project.commerce.domain.purchase.entity.Purchase;
import com.scb.project.commerce.domain.purchase.entity.PurchaseProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

    /**
     * 주문 객체를 기준으로 객체 찾는 메서드
     *
     * @param purchase 주문 객체
     * @return 주문-상품 객체 리스트
     */
    List<PurchaseProduct> findAllByPurchase(Purchase purchase);
}
