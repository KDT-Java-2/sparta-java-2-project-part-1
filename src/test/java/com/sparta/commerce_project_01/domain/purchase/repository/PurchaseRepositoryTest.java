package com.sparta.commerce_project_01.domain.purchase.repository;

import com.sparta.commerce_project_01.common.enums.PurchaseStatus;
import com.sparta.commerce_project_01.domain.purchase.entity.Purchase;
import com.sparta.commerce_project_01.domain.user.entity.User;
import com.sparta.commerce_project_01.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
//@Transactional
class PurchaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    void 저장() {
        User user = User.builder()
                .name("d1")
                .email("d1")
                .passwordHash("d1")
                .build();

        User savedUser = userRepository.save(user);

        Purchase purchase = Purchase.builder()
                .user(savedUser)
                .totalPrice(BigDecimal.valueOf(1000L))
                .status(PurchaseStatus.PENDING)
                .shippingAddress("서울시 강남구")
                .build();

        Purchase savePurchase = purchaseRepository.save(purchase);
    }

    @Test
    void 조회() {
        // --- 실행 코드 ---
        // User 조회 시점에 user와 purchase 테이블을 JOIN하여 모든 데이터를 한 번에 가져온다.
        User user = userRepository.findById(1L).get();
    }
}