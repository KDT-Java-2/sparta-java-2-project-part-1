package com.sparta.commerce.domain.product.dto.product;

import com.sparta.commerce.domain.product.dto.image.ProductImageResponse;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSummaryResponse {
  Long productId;
  String name;
  String brand;
  ProductImageResponse thumbnail;
  BigDecimal basePrice;
}
