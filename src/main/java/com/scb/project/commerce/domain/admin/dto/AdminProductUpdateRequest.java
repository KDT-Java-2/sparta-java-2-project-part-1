package com.scb.project.commerce.domain.admin.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProductUpdateRequest {

    // 상품명
    String name;

    // 브랜드명
    String brandName;

    // 상품 설명
    String description;

    // 상품 가격
    BigDecimal price;

    // 재고 수량
    Integer stock;

    // 카테고리 ID
    Long categoryId;
}
