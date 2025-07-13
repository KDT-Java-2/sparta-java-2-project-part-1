package com.sparta.commerce.domain.category.dto.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreateRequest {

  @NotBlank(message = "이름은 필수입니다")
  String name;

  Long parentId;

  String description;

}
