package com.sparta.e_project.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.sparta.e_project.domain.category.dto.CategoryResponse;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

  Long id;
  String name;
  String description;
  BigDecimal price;
  Integer stock;
  CategoryResponse category;

  @QueryProjection
  public ProductResponse(Long id, String name, String description, BigDecimal price, Integer stock,
      Long categoryId, String categoryName) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.category = CategoryResponse.builder().id(categoryId).name(categoryName).build();
  }
}
