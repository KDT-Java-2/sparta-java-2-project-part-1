package com.sparta.java_02.domain.refund.service;

import com.sparta.java_02.common.enums.PurchaseStatus;
import com.sparta.java_02.common.enums.RefundStatus;
import com.sparta.java_02.common.exception.ServiceException;
import com.sparta.java_02.common.exception.ServiceExceptionCode;
import com.sparta.java_02.domain.purchase.entity.Purchase;
import com.sparta.java_02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java_02.domain.refund.dto.RefundRequest;
import com.sparta.java_02.domain.refund.entity.Refund;
import com.sparta.java_02.domain.refund.repository.RefundRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundService {

  private final PurchaseRepository purchaseRepository;
  private final RefundRepository refundRepository;

  @Transactional
  public void requestRefund(RefundRequest request) {
    // 1. 구매 내역 조회 및 유효성 검증
    Purchase purchase = purchaseRepository.findById(request.getPurchaseId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PURCHASE));

    // 2. 이미 환불되었거나 취소된 주문인지 확인
    if (purchase.getStatus().equals(PurchaseStatus.CANCELLED)) {
      throw new ServiceException(ServiceExceptionCode.ALREADY_REFUNDED_ORDER);
    }

    // 3. 구매 내역 상태 변경 (변경 감지 활용)
    purchase.setStatus(PurchaseStatus.CANCELLED);

    // 4. Refund 엔티티 생성 및 저장
    Refund refund = Refund.builder()
        .purchase(purchase)
        .reasonCd(request.getReasonCd())
        .status(RefundStatus.REQUESTED)
        .build();

    refundRepository.save(refund);
  }
}