package com.dogworld.dogdog.api.product.response;

import com.dogworld.dogdog.api.category.response.CategoryProductResponse;
import com.dogworld.dogdog.domain.product.Product;
import com.dogworld.dogdog.domain.product.ProductStatus;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {

  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private int stock;
  private CategoryProductResponse category;
  private ProductStatus status;
  private String thumbnailUrl;

  public static ProductResponse from(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .category(CategoryProductResponse.from(product.getCategory()))
        .status(product.getStatus())
        .thumbnailUrl(product.getThumbnailUrl())
        .build();
  }
}
