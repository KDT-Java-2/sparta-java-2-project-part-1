package com.scb.project.commerce.domain.admin.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryUpdateRequest {

    // 카테고리명
    String name;

    // 부모 카테고리 ID
    Long parentId;
}
