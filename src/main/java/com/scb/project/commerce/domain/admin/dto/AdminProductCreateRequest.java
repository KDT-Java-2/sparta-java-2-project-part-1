package com.scb.project.commerce.domain.admin.dto;

import com.scb.project.commerce.common.enums.ProductStatus;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProductCreateRequest {

    // 상품명
    @NotNull
    String name;

    // 브랜드명
    @NotNull
    String brandName;

    // 상품 설명
    String description;

    // 상품 가격
    @NotNull
    BigDecimal price;

    // 재고 수량
    @NotNull
    Integer stock;

    // 카테고리 ID
    @NotNull
    Long categoryId;


    // 기본값 설정
    final ProductStatus status = ProductStatus.ON_SALE;
}
