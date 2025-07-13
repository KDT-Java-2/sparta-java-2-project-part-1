package com.sparta.commerce.domain.product.dto;

import com.sparta.commerce.domain.category.dto.CategoryProductResponse;
import com.sparta.commerce.domain.category.entity.Category;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {

  Long id;

  String name;

  CategoryProductResponse category;

  String description;

  BigDecimal price;

  Integer stock;

}
