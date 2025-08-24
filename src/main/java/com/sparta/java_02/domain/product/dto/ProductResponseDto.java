package com.sparta.java_02.domain.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseDto {

  private final Long id;
  private final String name;
  private final String description;
  private final BigDecimal price;
  private final Integer stock;
  private final String categoryName;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;
}