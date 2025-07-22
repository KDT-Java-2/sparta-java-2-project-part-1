package com.sparta.bootcamp.java_2_example.domain.product.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest {

  Long category;

  Integer minPrice;

  Integer maxPrice;

}
