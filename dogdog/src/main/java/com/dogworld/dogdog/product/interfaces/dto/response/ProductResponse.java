package com.dogworld.dogdog.product.interfaces.dto.response;

import com.dogworld.dogdog.category.interfaces.dto.response.CategoryProductResponse;
import com.dogworld.dogdog.product.domain.Product;
import com.dogworld.dogdog.product.domain.ProductStatus;
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
