package com.example.shoppingmall.domain.product.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
    Long id;
    String name;
    String description;
    BigDecimal price;
    Integer stock;
    List<String> imageUrls;
    Long categoryId;
    String categoryName;
    List<ProductOptionDto> variants;
}
