package com.sparta.coupang_commerce.domain.category.dto.admin;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreateOrUpdateRequest {

  @NotNull
  String name;

  Long parentId;
}
