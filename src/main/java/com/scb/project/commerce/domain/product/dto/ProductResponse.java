package com.scb.project.commerce.domain.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    // 상품 아이디
    Long id;

    // 상품명
    String productName;

    // 브랜드명
    String brandName;

    // 상품 가격
    Long price;

    // 재고 수량
    int stock;

    // 카테고리 정보
    ProductCategoryResponse category;
}
