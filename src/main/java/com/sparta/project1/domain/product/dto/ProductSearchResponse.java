package com.sparta.project1.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchResponse {
    Long id;

    String name;

    BigDecimal price;

    Integer stock;

    @QueryProjection
    public ProductSearchResponse(Long id, String name, BigDecimal price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
