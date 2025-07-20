package com.sparta.commerce.domain.product.dto.product.productItem;


import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductItemResponse {
  Long itemId;
  List<Long> optionItemIds; // ex: [101, 201]
  BigDecimal price;
  int stock;
}
