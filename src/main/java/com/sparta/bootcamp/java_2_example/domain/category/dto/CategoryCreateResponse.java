package com.sparta.bootcamp.java_2_example.domain.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreateResponse {

    Long id;

    public CategoryCreateResponse(
            Long id
    ) {
        this.id = id;
    }

}
