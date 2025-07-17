package com.scb.masterCourse.commerce.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBrandResponse {

    Long id;

    String seller;

    String name;

    String description;

    @QueryProjection
    public ProductBrandResponse(Long id, String seller, String name, String description) {
        this.id = id;
        this.seller = seller;
        this.name = name;
        this.description = description;
    }
}
