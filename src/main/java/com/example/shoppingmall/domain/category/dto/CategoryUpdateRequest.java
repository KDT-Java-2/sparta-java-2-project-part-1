package com.example.shoppingmall.domain.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateRequest {
    String name;      // 카테고리명 (필수)
    Long parentId;    // 부모 카테고리 ID (선택, null이면 최상위)
} 