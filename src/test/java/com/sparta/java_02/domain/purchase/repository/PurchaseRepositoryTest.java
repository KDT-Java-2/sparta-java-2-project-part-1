package com.sparta.java_02.domain.purchase.repository;

import com.sparta.java_02.common.enums.PurchaseStatus;
import com.sparta.java_02.domain.purchase.entity.Purchase;
import com.sparta.java_02.domain.user.entity.User;
import com.sparta.java_02.domain.user.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PurchaseRepositoryTest {

  @Autowired
  private PurchaseRepository purchaseRepository;
  @Autowired
  private UserRepository userRepository;

//  @Test
//  void 저장() {
//    User user = userRepository.save(User.builder()
//        .name("d")
//        .email("d")
//        .passwordHash("d")
//        .build());
//
//    Purchase purchase = Purchase.builder()
//        .user(user)
//        .totalPrice(BigDecimal.valueOf(1000))
//        .status(PurchaseStatus.PENDING)
//        .build();
//
//    Purchase savePurchase = purchaseRepository.save(purchase);
//
//
//  }
//
//  @Test
//  void 수정() {
//    User user = userRepository.save(User.builder()
//        .name("d")
//        .email("d")
//        .passwordHash("d")
//        .build());
//
//    Purchase purchase = Purchase.builder()
//        .user(user)
//        .totalPrice(BigDecimal.valueOf(1000))
//        .status(PurchaseStatus.PENDING)
//        .build();
//
//    Purchase savePurchase = purchaseRepository.save(purchase);
//
//    savePurchase.setStatus(PurchaseStatus.COMPLETION);
//    purchaseRepository.save(purchase);
//
//  }
//
//  @Test
//  void 삭제() {
//
//    User user = userRepository.save(User.builder()
//        .name("d")
//        .email("d")
//        .passwordHash("d")
//        .build());
//
//    Purchase purchase = Purchase.builder()
//        .user(user)
//        .totalPrice(BigDecimal.valueOf(1000))
//        .status(PurchaseStatus.PENDING)
//        .build();
//
//    Purchase savePurchase = purchaseRepository.save(purchase);
//
//    purchaseRepository.deleteAll();
//  }
//
//  @Test
//  void 조회() {
//
//    List<Purchase> purchases = purchaseRepository.findAll();
//
//    for (Purchase purchase : purchases) {
//      System.out.println("유저 이름: " + purchase.getUser().getName()); // N쿼리 (사용자 수만큼 추가)
//    }
//
//  }

}
