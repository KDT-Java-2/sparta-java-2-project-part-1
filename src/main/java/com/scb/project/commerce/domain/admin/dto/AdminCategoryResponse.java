package com.scb.project.commerce.domain.admin.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryResponse {

    // 카테고리 id
    Long id;

    // 카테고리명
    String name;

    // 부모 카테고리 ID
    Long parentId;
}
