package com.sparta.spartajava2projectpart1.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateRequest {
    @NotNull
    Long id;
    @NotBlank
    String name;
    @NotBlank
    String description;
    @PositiveOrZero
    BigDecimal price;
    @PositiveOrZero
    Integer stock;
    @NotNull
    Long categoryId;
}
