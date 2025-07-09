package com.sparta.project1.domain.product.dto.admin;

import com.sparta.project1.domain.category.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAdminRegisterRequest {

    @NotNull
    String name;

    @NotNull
    String description;

    @Positive
    @NotNull
    BigDecimal price;

    @Positive
    @NotNull
    Integer stock;

    @NotNull
    Long categoryId;

}
