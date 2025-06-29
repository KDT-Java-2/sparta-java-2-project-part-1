package com.sparta.spartajava2projectpart1.domain.category.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    Long id;
    String name;
    List<CategoryResponse> children = new ArrayList<>();
}
