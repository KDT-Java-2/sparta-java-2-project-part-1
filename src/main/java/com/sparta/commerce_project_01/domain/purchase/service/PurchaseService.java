package com.sparta.commerce_project_01.domain.purchase.service;

import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import com.sparta.commerce_project_01.common.enums.exception.ServiceExceptionCode;
import com.sparta.commerce_project_01.domain.purchase.dto.PurchaseCancelRequest;
import com.sparta.commerce_project_01.domain.purchase.dto.PurchaseCancelResponse;
import com.sparta.commerce_project_01.domain.purchase.dto.PurchaseRequest;
import com.sparta.commerce_project_01.domain.purchase.entity.Purchase;
import com.sparta.commerce_project_01.domain.purchase.repository.PurchaseRepository;
import com.sparta.commerce_project_01.domain.user.entity.User;
import com.sparta.commerce_project_01.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseProcessService purchaseProcessService;
  private final PurchaseCancelService purchaseCancelService;

  private final UserRepository userRepository;
  private final PurchaseRepository purchaseRepository;

  @Transactional
  public Purchase purchase(PurchaseRequest request) {
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND));

    return purchaseProcessService.process(user, request.getProducts());
  }

  @Transactional
  public PurchaseCancelResponse cancel(PurchaseCancelRequest request) {
    // user 검증은 Auth 에서 수행 했다고 가정
    return purchaseCancelService.cancelPurchase(request.getPurchaseId(), request.getUserId());
  }

}