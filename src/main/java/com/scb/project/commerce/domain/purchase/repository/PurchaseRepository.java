package com.scb.project.commerce.domain.purchase.repository;

import com.scb.project.commerce.domain.purchase.entity.Purchase;
import com.scb.project.commerce.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    /**
     * 사용자가 주문한 주문 객체 찾는 메서드
     *
     * @param user 사용자 객체
     * @return 주문 객체 리스트
     */
    List<Purchase> findAllByUser(User user);
}
