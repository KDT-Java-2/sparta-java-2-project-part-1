package com.scb.masterCourse.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCategoryResponse {

    Long id;

    String name;

    @QueryProjection
    public ProductCategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
