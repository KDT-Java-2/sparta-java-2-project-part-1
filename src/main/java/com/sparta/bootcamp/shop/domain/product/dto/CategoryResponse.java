package com.sparta.bootcamp.shop.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

    private final Long id;
    private final String name;

    @Builder
    @QueryProjection
    public CategoryResponse(
            Long id,
            String name
    ) {
        this.id = id;
        this.name = name;

    }
}
