package com.sparta.javamarket.domain.product.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.wildfly.common.annotation.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {

  Long categoryId;

  @NotNull
  String name;

  String description;

  @NotNull
  @Positive // 값을 양수로만 제한
  BigDecimal price;

  @NotNull
  @PositiveOrZero // 값을 양수로 혹은 0으로 제한
  Integer stock;

}