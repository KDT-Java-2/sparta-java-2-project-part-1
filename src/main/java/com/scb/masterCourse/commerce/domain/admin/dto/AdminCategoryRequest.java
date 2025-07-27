package com.scb.masterCourse.commerce.domain.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryRequest {

    @NotBlank(message = "카테고리명은 필수입니다.")
    String name;

    Long parentId;
}
