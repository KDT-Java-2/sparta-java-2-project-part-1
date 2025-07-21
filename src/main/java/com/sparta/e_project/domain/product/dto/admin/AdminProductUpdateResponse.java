package com.sparta.e_project.domain.product.dto.admin;

import com.sparta.e_project.domain.category.dto.CategoryProductResponse;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AdminProductUpdateResponse {

  Long productId;
  String name;
  String description;
  BigDecimal price;
  Integer stock;
  CategoryProductResponse category;
}
