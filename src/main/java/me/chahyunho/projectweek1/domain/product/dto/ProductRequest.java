package me.chahyunho.projectweek1.domain.product.dto;


import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

  @NotNull(message = "name is required")
  String name;

  @NotNull
  String description;

  @NotNull
  BigDecimal price;

  @NotNull
  Integer stock;
}
