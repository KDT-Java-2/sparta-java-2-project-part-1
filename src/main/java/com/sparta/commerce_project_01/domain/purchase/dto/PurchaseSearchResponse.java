package com.sparta.commerce_project_01.domain.purchase.dto;

import com.sparta.commerce_project_01.common.enums.PurchaseStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseSearchResponse {

  Long id;
  String user_id;
  Integer quantity;
  PurchaseStatus status;
  BigDecimal totalPrice;
  LocalDateTime createdAt;
}
