package com.sparta.commerce.domain.product.dto.product;

import com.sparta.commerce.domain.product.repository.ProductOrder;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchCondition {
  private Long categoryId;
  private BigDecimal minPrice;
  private BigDecimal maxPrice;
  private String keyword;
  private ProductOrder sort;
}
