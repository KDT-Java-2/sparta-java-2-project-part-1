package com.sparta.javamarket.domain.admin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryCreateRequest {

  @NotNull(message = "카테고리명은 필수입력 항목입니다.")
  String name;

  String description;

  Long parentId;

}
