package com.scb.project.commerce.domain.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCategoryResponse {

    // 카테고리 아이디
    Long id;

    // 카테고리명
    String name;
}
