package me.chahyunho.projectweek1.domain.product.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import me.chahyunho.projectweek1.domain.category.dto.CategoryResponse;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

  Long id;
  String name;
  String description;
  BigDecimal price;
  Integer stock;
  CategoryResponse category;
}
