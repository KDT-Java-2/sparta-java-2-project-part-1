package com.sparta.e_project.domain.product.dto.admin;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AdminProductResponse {

  private Long productId;
}
