package com.example.shoppingmall.domain.product.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateResponse {
    Long productId;
    String name;
    String description;
    BigDecimal price;
    Integer stock;
    Long categoryId;
    String categoryName;
    List<ProductOptionDto> variants;
    List<String> imageUrls;
} 