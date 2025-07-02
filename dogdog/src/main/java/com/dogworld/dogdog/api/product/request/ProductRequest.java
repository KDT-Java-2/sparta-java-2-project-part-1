package com.dogworld.dogdog.api.product.request;

import com.dogworld.dogdog.domain.product.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class ProductRequest {

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @NotNull
  @PositiveOrZero
  private Integer price;

  @NotNull
  @PositiveOrZero
  private Integer stock;

  @NotNull
  @Positive
  private Long categoryId;

  private ProductStatus status;

  private String thumbnailUrl;

}
