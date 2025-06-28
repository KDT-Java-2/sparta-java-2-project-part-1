package me.chahyunho.projectweek1.domain.purchase.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequest {

  @NotNull(message = "userId must not be null")
  Long userId;

  @NotNull(message = "productId must not be null")
  Long productId;

  @NotNull(message = "quantity must not be null")
  Integer quantity;

  @NotNull(message = "shippingAddress must not be null")
  String shippingAddress;
}
