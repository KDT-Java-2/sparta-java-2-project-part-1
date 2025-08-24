package com.sparta.java_02.domain.refund.controller;

import com.sparta.java_02.common.dto.ApiResponse;
import com.sparta.java_02.domain.refund.dto.RefundRequest;
import com.sparta.java_02.domain.refund.service.RefundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refunds")
@RequiredArgsConstructor
public class RefundController {

  private final RefundService refundService;

  // 환불 요청 API
  @PostMapping
  public ResponseEntity<ApiResponse<Void>> requestRefund(
      @Valid @RequestBody RefundRequest request) {

    refundService.requestRefund(request);

    ApiResponse<Void> apiResponse = ApiResponse.success(HttpStatus.NO_CONTENT, null);

    return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
  }
}