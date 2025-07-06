package com.sparta.commerce_project_01.domain.purchase.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.wildfly.common.annotation.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseProductRequest {

  @NotNull
  Long productId;

  @NotNull
  Integer quantity;
}