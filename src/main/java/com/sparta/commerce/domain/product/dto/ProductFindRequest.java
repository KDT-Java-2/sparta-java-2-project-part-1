package com.sparta.commerce.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFindRequest {
  Long categoryId;

  BigDecimal minPrice;

  BigDecimal maxPrice;

  @JsonProperty(defaultValue = "0")
  Integer page;

  @JsonProperty(defaultValue = "10")
  Integer size;

  String sortBy;
}
