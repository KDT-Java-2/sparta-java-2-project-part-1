package com.scb.project.commerce.domain.admin.dto;

import com.scb.project.commerce.common.enums.ProductStatus;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProductUpdateResponse {

    // 상품 ID
    Long productId;

    // 상품명
    String productName;

    // 브랜드명
    String brandName;

    // 상품 설명
    String description;

    // 상품 가격
    BigDecimal price;

    // 재고 수량
    Integer stock;

    // 판매 상태
    ProductStatus status;

    // 카테고리 ID
    Long categoryId;

    // 카테고리명
    String categoryName;
}
