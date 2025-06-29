package com.scb.project.commerce.domain.admin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryCreateRequest {

    // 카테고리명
    @NotNull
    String name;

    // 부모 카테고리 ID
    Long parentId;
}
