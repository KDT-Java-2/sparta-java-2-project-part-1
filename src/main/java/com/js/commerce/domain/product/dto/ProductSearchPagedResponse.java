package com.js.commerce.domain.product.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchPagedResponse {

  List<ProductSearchResponse> content;

  PageInfo pageable;

  int totalPages;

  long totalElements;

}
