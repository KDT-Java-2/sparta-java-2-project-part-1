package com.socialcommerce.domain.purchase.controller;

import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.auth.dto.CustomUserDetails;
import com.socialcommerce.domain.purchase.dto.PurchaseCreateRequest;
import com.socialcommerce.domain.purchase.dto.PurchaseResponse;
import com.socialcommerce.domain.purchase.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
  public ApiResponse<PurchaseResponse> createPurchaseFromCart(@RequestBody @Valid PurchaseCreateRequest request,
      @AuthenticationPrincipal CustomUserDetails user){
    PurchaseResponse purchaseResponse = purchaseService.createPurchaseFromCart(user.getId(), request);
    return ApiResponse.success(purchaseResponse);
  }
}
