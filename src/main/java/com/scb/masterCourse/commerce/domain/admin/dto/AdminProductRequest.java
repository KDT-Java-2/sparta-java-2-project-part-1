package com.scb.masterCourse.commerce.domain.admin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProductRequest {

    @NotBlank(message = "상품명은 필수입니다.")
    String name;

    @NotNull(message = "브랜드는 필수입니다.")
    Long brandId;

    @NotNull(message = "카테고리는 필수입니다.")
    Long categoryId;

    String description;

    @NotNull(message = "상품 가격은 필수입니다.")
    @Min(value = 0, message = "상품 가격은 0원 이상이어야 합니다.")
    Integer price;

    @NotNull(message = "재고 수량은 필수입니다.")
    @Min(value = 0, message = "재고 수량은 0개 이상이어야 합니다.")
    Integer stock;
}
