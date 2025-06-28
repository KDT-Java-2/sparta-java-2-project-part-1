package me.chahyunho.projectweek1.domain.product.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {

  @NotNull(message = "name must not be null")
  String name;

  @NotNull(message = "description must not be null")
  String description;

  @NotNull(message = "price must not be null")
  BigDecimal price;

  @NotNull(message = "stock must not be null")
  Integer stock;
}
