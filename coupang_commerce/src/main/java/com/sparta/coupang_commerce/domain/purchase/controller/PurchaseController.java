package com.sparta.coupang_commerce.domain.purchase.controller;


import com.sparta.coupang_commerce.common.response.ApiResponse;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseCancelRequest;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseCancelResponse;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseResponse;
import com.sparta.coupang_commerce.domain.purchase.service.PurchaseService;
import com.sparta.coupang_commerce.domain.user.dto.UserCreateRequest;
import com.sparta.coupang_commerce.domain.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/purchases")
@RestController
@RequiredArgsConstructor
public class PurchaseController {

  private final PurchaseService purchaseService;

  @GetMapping("/{purchaseId}")
  public ApiResponse<List<PurchaseResponse>> findAll(@PathVariable Long purchaseId, @RequestParam String email) {
    List<PurchaseResponse> searchResponseList = List.of(
        PurchaseResponse.builder().build(),
        PurchaseResponse.builder().build()
    );
    return ApiResponse.success(searchResponseList);
  }

  @PostMapping
  public ResponseEntity<Void> save(@Valid @RequestBody UserCreateRequest request) {
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{purchaseId}")
  public ResponseEntity<Void> update(@PathVariable Long purchaseId, @RequestBody UserUpdateRequest request) {
    return ResponseEntity.ok().build();
  }

  @PatchMapping
  public ResponseEntity<Void> updateStatus() {
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ApiResponse<PurchaseCancelResponse> delete(@RequestBody PurchaseCancelRequest request) {
    return ApiResponse.success(purchaseService.cancelPurchase(request));
  }

}
