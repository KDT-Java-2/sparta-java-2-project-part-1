package com.sparta.bootcamp.java_2_example.domain.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateResponse {

    Long id;

    public ProductCreateResponse(
            Long id
    ) {
        this.id = id;
    }

}
