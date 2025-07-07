package com.sparta.commerce.domain.product.dto.product;

import com.sparta.commerce.domain.product.dto.image.ProductImageResponse;
import com.sparta.commerce.domain.product.dto.option.ProductOptionResponse;
import com.sparta.commerce.domain.product.entity.Product;
import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
  Long productId;
  String productName;
  String brand;
  String description;
  BigDecimal basePrice;

  List<ProductImageResponse> images;
  List<ProductOptionResponse> options;

  public static ProductDetailResponse of(Product product, List<ProductImageResponse> images, List<ProductOptionResponse> options){
    return ProductDetailResponse.builder()
        .productId(product.getId())
        .productName(product.getName())
        .brand(product.getBrand())
        .description(product.getDescription())
        .basePrice(product.getBasePrice())
        .images(images)
        .options(options)
        .build();

  }
}
