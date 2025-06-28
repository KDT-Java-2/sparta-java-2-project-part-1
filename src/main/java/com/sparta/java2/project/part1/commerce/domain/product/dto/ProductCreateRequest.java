package com.sparta.java2.project.part1.commerce.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {
    /*
    - **요청 (Request Body)**:
    - `name` (String, 필수): 상품명. 시스템 내에서 **고유해야 합니다(Unique)**.
    - `description` (String, 필수): 상품의 상세 설명.
    - `price` (Integer, 필수): 상품 가격. **0 이상의 정수**여야 합니다.
    - `stock` (Integer, 필수): 재고 수량. **0 이상의 정수**여야 합니다.
    - `categoryId` (Long, 필수): 상품이 속할 카테고리의 ID.
     */
    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotNull
    @PositiveOrZero
    Integer price;

    @NotNull
    @PositiveOrZero
    Integer stock;

    @NotNull
    Long categoryId;
}
