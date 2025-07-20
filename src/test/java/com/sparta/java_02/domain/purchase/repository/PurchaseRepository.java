package com.sparta.java_02.domain.purchase.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
public class PurchaseRepository {

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Test
  void 성공() {
  }

}
