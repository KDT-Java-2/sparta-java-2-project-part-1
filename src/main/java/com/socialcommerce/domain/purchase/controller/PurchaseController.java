package com.socialcommerce.domain.purchase.controller;

import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.purchase.dto.PurchaseRequest;
import com.socialcommerce.domain.purchase.dto.PurchaseResponse;
import com.socialcommerce.domain.purchase.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {
  private final PurchaseService purchaseService;

  @PostMapping("/from-cart")
  public ApiResponse<PurchaseResponse> createPurchaseFromCart(@RequestBody @Valid PurchaseRequest request){
    PurchaseResponse purchaseResponse = purchaseService.createPurchaseFromCart(request);
    return ApiResponse.success(purchaseResponse);
  }
}
