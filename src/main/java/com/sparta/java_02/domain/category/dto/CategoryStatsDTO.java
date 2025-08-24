package com.sparta.java_02.domain.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CategoryStatsDTO {

    private final String categoryName;
    private final Long productCount;
    private final Long totalStock;

    @QueryProjection
    public CategoryStatsDTO(String categoryName, Long productCount, Long totalStock) {
        this.categoryName = categoryName;
        this.productCount = productCount;
        this.totalStock = totalStock;
    }
}