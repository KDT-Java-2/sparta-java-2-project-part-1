package me.chahyunho.projectweek1.domain.admin.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryUpdateResponse {

  String name;
  String description;
  Long parentId;
}
