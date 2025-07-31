package com.socialcommerce.domain.purchase.service;

import com.socialcommerce.domain.purchase.dto.PurchaseRequest;
import com.socialcommerce.domain.purchase.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;

//  @Transactional
//  public PurchaseResponse createPurchase(PurchaseRequest request){
//    return null;
//  }
}
