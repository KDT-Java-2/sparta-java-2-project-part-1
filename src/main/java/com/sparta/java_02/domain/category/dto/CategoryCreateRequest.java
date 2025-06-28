package com.sparta.java_02.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryCreateRequest {
    
    @NotBlank(message = "카테고리명은 필수입니다.")
    private String name;
    
    private String description;
    
    private Long parentId;  // null이면 최상위 카테고리

    public CategoryCreateRequest(String name, String description, Long parentId) {
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }
} 