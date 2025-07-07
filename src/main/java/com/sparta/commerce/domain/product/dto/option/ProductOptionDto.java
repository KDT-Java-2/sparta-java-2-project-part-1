package com.sparta.commerce.domain.product.dto.option;

import com.sparta.commerce.domain.product.entity.OptionItem;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOptionDto {
  Long optionGroupId;
  String optionGroupName;
  Long optionItemId;
  String optionItemName;
  BigDecimal additionalPrice;

  public static ProductOptionDto of(OptionItem optionItem) {
    return ProductOptionDto.builder()
        .optionGroupId(optionItem.getOptionGroup().getId())
        .optionGroupName(optionItem.getOptionGroup().getName())
        .optionItemId(optionItem.getId())
        .optionItemName(optionItem.getValue())
        .additionalPrice(optionItem.getAdditionalPrice())
        .build();
  }
}
