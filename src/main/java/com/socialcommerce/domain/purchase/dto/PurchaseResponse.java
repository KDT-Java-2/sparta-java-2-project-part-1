package com.socialcommerce.domain.purchase.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseResponse {
  private Long purchaseId;
  private String status;
  private BigDecimal totalPrice;
  private String shippingAddress;
  private LocalDateTime createdAt;
  private List<PurchaseProductResponse> products;

}
