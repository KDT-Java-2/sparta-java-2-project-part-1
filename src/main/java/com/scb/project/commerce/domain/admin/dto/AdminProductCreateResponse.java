package com.scb.project.commerce.domain.admin.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProductCreateResponse {

    // 상품 ID
    Long productId;
}
