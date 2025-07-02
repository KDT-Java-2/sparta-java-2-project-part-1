package com.sparta.part_1.domain.product.dto.response;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateResponse {

  Long id;

  Integer stock;

  BigDecimal price;

  String description;

  String name;

  Long categoryId;

}
