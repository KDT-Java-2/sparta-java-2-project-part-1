package com.sparta.part_1.domain.category.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEditRequest {

  String name;

  String description;

  Long parentId;

}
