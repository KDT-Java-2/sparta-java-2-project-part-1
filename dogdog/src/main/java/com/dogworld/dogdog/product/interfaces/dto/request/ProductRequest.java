package com.dogworld.dogdog.product.interfaces.dto.request;

import com.dogworld.dogdog.product.domain.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class ProductRequest {

  @NotBlank(message = "{validation.product.name.required}")
  private String name;

  @NotBlank(message = "{validation.product.description.required}")
  private String description;

  @NotNull(message = "{validation.product.price.required}")
  @PositiveOrZero(message = "{validation.price.positiveOrZero}")
  private Integer price;

  @NotNull(message = "{validation.product.stock.required}")
  @PositiveOrZero(message = "{validation.stock.positiveOrZero}")
  private Integer stock;

  @NotNull(message = "{validation.product.category.required}")
  @Positive(message = "{validation.product.categoryId.positive}")
  private Long categoryId;

  private ProductStatus status;

  private String thumbnailUrl;

}
