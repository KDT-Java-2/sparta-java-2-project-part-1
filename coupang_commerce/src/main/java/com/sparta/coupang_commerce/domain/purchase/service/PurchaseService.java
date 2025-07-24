package com.sparta.coupang_commerce.domain.purchase.service;

import com.sparta.coupang_commerce.common.exception.ServiceException;
import com.sparta.coupang_commerce.common.exception.ServiceExceptionCode;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseCancelRequest;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseCancelResponse;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseRequest;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseResponse;
import com.sparta.coupang_commerce.domain.user.entity.User;
import com.sparta.coupang_commerce.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final UserRepository userRepository;
  private final PurchaseProcessService purchaseProcessService;
  private final PurchaseCancelService purchaseCancelService;

  @Transactional
  public PurchaseResponse createPurchase(PurchaseRequest request) {
    User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    return purchaseProcessService.process(user, request.getPurchaseProducts());
  }

  @Transactional
  public PurchaseCancelResponse cancelPurchase(PurchaseCancelRequest request) {
    return purchaseCancelService.cancel(request.getPurchaseId(), request.getUserId());
  }
}
