package com.sparta.commerce.domain.product.dto.image;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageResponse {
  Long productId;
  String imageUrl;
  boolean isThumbnail;
}
