package com.sparta.java_02.domain.purchase.controller;

import com.sparta.java_02.common.dto.ApiResponse;
import com.sparta.java_02.domain.purchase.dto.PurchaseRequest;
import com.sparta.java_02.domain.purchase.dto.PurchaseResponse;
import com.sparta.java_02.domain.purchase.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {

  private final PurchaseService purchaseService;

  // 구매(주문) 생성 API
  @PostMapping
  public ResponseEntity<ApiResponse<PurchaseResponse>> placePurchase(
      @Valid @RequestBody PurchaseRequest request) {

    PurchaseResponse responseDto = purchaseService.placePurchase(request);

    ApiResponse<PurchaseResponse> apiResponse = ApiResponse.success(HttpStatus.CREATED,
        responseDto);

    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }
}