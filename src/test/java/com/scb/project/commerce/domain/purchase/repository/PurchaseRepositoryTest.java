package com.scb.project.commerce.domain.purchase.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.project.commerce.common.enums.PaymentMethod;
import com.scb.project.commerce.common.enums.PurchaseStatus;
import com.scb.project.commerce.domain.purchase.entity.Purchase;
import com.scb.project.commerce.domain.user.entity.User;
import com.scb.project.commerce.domain.user.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class PurchaseRepositoryTest {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("기본 CRUD : 주문 한 건 조회에 성공합니다.")
    void selectOnePurchaseSuccess() {
        // given
        User user = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습닌다."));

        // when
        Purchase purchase = purchaseRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));

        // then
        assertThat(purchase.getUser()).isEqualTo(user);
    }

    @Test
    @DisplayName("기본 CRUD : 모든 주문 조회에 성공합니다.")
    void selectAllPurchaseSuccess() {
        // when
        List<Purchase> purchases = purchaseRepository.findAll();

        // then
        assertThat(purchases.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("기본 CRUD : 주문 생성에 성공합니다.")
    void insertPurchaseSuccess() {
        // given
        User user = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        Purchase savePurchase = Purchase.builder()
            .user(user)
            .totalPrice(BigDecimal.valueOf(30000))
            .paymentMethod(PaymentMethod.KAKAO_PAY)
            .status(PurchaseStatus.PENDING)
            .shippingAddress("우리집")
            .memo("부재 시 경비실에 맡겨주세요.")
            .build();

        // when
        purchaseRepository.save(savePurchase);

        // then
        Purchase purchase = purchaseRepository.findById(3L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));

        assertThat(purchase.getUser()).isEqualTo(user);
    }

    @Test
    @DisplayName("기본 CRUD : 주문 수정에 성공합니다.")
    void updatePurchaseSuccess() {
        // given
        Purchase originPurchase = purchaseRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));

        originPurchase.setStatus(PurchaseStatus.PAID);

        // when
        purchaseRepository.save(originPurchase);

        // then
        Purchase updatePurchase = purchaseRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));

        assertThat(updatePurchase.getStatus()).isEqualTo(PurchaseStatus.PAID);
    }

    @Test
    @DisplayName("기본 CRUD : 주문 한 건 삭제에 성공합니다.")
    void deletePurchaseSuccess() {
        // given
        Purchase purchase = purchaseRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));

        // when
        purchaseRepository.delete(purchase);

        // then
        boolean isExists = purchaseRepository.existsById(1L);
        assertThat(isExists).isFalse();
    }

    @Test
    @DisplayName("기본 CRUD : 사용자가 주문한 모든 주문 삭제에 성공합니다.")
    void deleteAllUserPurchaseSuccess() {
        // given
        User user = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        List<Purchase> user1Purchases = purchaseRepository.findAllByUser(user);

        // when
        purchaseRepository.deleteAll(user1Purchases);

        // then
        boolean isEmpty = purchaseRepository.findAllByUser(user).isEmpty();
        assertThat(isEmpty).isTrue();
    }
}