package com.sparta.bootcamp.java_2_example.domain.category.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryHierarchyResponse {

    Long id;

    String name;

    List<CategoryHierarchyResponse> children;

}
