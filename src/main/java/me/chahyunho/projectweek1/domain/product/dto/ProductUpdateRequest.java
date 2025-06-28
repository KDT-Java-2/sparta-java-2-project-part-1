package me.chahyunho.projectweek1.domain.product.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {

  @NotNull(message = "id must not be null")
  Long id;

  String name;

  String description;

  BigDecimal price;

  Integer stock;
}
