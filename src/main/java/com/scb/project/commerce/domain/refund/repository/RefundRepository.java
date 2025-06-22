package com.scb.project.commerce.domain.refund.repository;

import com.scb.project.commerce.domain.purchase.entity.Purchase;
import com.scb.project.commerce.domain.refund.entity.Refund;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    /**
     * 주문 객체가 환불한 내용 찾는 메서드
     *
     * @param purchase 주문 객체
     * @return 환불 객체 리스트
     */
    List<Refund> findAllByPurchase(Purchase purchase);
}
