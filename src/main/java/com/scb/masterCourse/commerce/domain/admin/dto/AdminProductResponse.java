package com.scb.masterCourse.commerce.domain.admin.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProductResponse {

    Long productId;

    String productName;

    String brandName;

    String categoryName;

    String description;

    Integer price;

    Integer stock;

    String status;
}
