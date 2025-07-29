package com.sparta.commerce_project_01.domain.purchase.service;

import com.sparta.commerce_project_01.common.enums.PurchaseStatus;
import com.sparta.commerce_project_01.domain.purchase.dto.PurchaseSearchCondition;
import com.sparta.commerce_project_01.domain.purchase.dto.PurchaseSearchResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class PurchaseServiceTest {

  @Autowired
  private PurchaseService purchaseService;

  @Test
  void findPurchaseWithPagination() {

    // 1번 고객의 'COMPLETED' 상태 구매 내역 조회
    PurchaseSearchCondition condition = new PurchaseSearchCondition();
    condition.setUserId(1L);
    condition.setStatus(PurchaseStatus.COMPLETED.name());
    condition.setLimit(10);
    condition.setOffset(0);

    List<PurchaseSearchResponse> purchases = purchaseService.findPurchaseWithPagination(condition);


  }
}