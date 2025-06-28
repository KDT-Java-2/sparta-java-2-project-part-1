package me.chahyunho.projectweek1.domain.admin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryRequest {

  @NotNull
  String name;
  String description;
  Long parentId;
}
