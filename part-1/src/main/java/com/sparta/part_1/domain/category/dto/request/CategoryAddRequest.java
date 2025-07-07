package com.sparta.part_1.domain.category.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryAddRequest {

  @NotNull(message = "카테고리명이 없습니다.")
  String name;
  @NotNull(message = "설명을 등록해주세요!")
  String description;
  Long parentId;
}
