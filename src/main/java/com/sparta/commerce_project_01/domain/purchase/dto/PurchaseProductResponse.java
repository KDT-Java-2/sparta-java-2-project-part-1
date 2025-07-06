package com.sparta.commerce_project_01.domain.purchase.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseProductResponse {

  Long productId;
  String productName;
  Integer quantity;
  BigDecimal price;
  BigDecimal totalPrice;
}
