package com.dogworld.dogdog.api.product.request;

import com.dogworld.dogdog.domain.product.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class ProductRequest {

  @NotBlank
  private String name;

  private String description;

  @Positive
  private BigDecimal price;

  @PositiveOrZero
  private int stock;

  @NotBlank @Positive
  private Long categoryId;

  private ProductStatus status;

  private String thumbnailUrl;

}
