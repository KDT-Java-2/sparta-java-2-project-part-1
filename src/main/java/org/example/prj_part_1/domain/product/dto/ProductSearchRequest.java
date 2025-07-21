package org.example.prj_part_1.domain.product.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest {

  Long category;
  Integer minPrice;
  Integer maxPrice;
  Integer page = 0;
  Integer size = 10;
  String sortBy = "createdAt,desc";

}
