package com.sparta.bootcamp.java_2_example.domain.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

    Long id;

    String name;

    String description;

    @Setter
    CategoryResponse parent;

    public CategoryResponse(
            Long id,
            String name,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
