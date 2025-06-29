package com.scb.project.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchResponse {

    // 상품 ID
    final Long id;

    // 상품명
    final String productName;

    // 가격
    final BigDecimal price;

    // 재고 수량
    final Integer stock;

    // QueryDSL의 프로젝션 기능을 사용하기 위한 생성자
    @QueryProjection
    public ProductSearchResponse(
        Long id,
        String productName,
        BigDecimal price,
        Integer stock
    ) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }
}
