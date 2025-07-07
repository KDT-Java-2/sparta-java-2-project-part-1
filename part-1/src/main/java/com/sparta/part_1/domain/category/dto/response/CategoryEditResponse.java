package com.sparta.part_1.domain.category.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEditResponse {

  Long categoryId;

  Long parentId;

  String description;

  String name;

}
