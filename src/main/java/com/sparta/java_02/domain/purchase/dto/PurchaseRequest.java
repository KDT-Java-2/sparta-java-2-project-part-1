package com.sparta.java_02.domain.purchase.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PurchaseRequest {

  @NotNull(message = "사용자 ID는 필수입니다.")
  private Long userId;

  @NotNull(message = "상품 ID는 필수입니다.")
  private Long productId;

  @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
  private Integer quantity;

  @NotBlank(message = "배송 주소는 필수입니다.")
  private String shippingAddress;
}