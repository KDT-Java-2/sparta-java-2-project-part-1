package com.sparta.commerce.domain.product.repository.queryDsl;

import static com.sparta.commerce.domain.product.entity.QOptionGroup.optionGroup;
import static com.sparta.commerce.domain.product.entity.QOptionItem.optionItem;
import static com.sparta.commerce.domain.product.entity.QProductItem.productItem;
import static com.sparta.commerce.domain.product.entity.QProductItemOption.productItemOption;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.domain.product.dto.option.ProductOptionCombinationDto;
import com.sparta.commerce.domain.product.dto.option.ProductOptionDto;
import com.sparta.commerce.domain.product.dto.option.ProductOptionResponse;
import com.sparta.commerce.domain.product.dto.option.QProductOptionCombinationDto;
import com.sparta.commerce.domain.product.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductOptionQueryRepository {

  private final JPAQueryFactory queryFactory;

  public List<ProductOptionResponse> findOptionCombination(Product product){
    List<ProductOptionCombinationDto> optionCombinations = queryFactory
        .select(new QProductOptionCombinationDto(
            productItem.id,
            productItem.stock,
            optionGroup.id,
            optionGroup.name,
            optionItem.id,
            optionItem.value,
            optionItem.additionalPrice
        ))
        .from(productItemOption)
        .join(productItemOption.productItem, productItem)
        .join(productItemOption.optionItem, optionItem)
        .join(optionItem.optionGroup, optionGroup)
        .where(productItem.product.eq(product))
        .fetch();

    Map<Long, ProductOptionResponse> resultMap = new HashMap<>();
    for (ProductOptionCombinationDto dto : optionCombinations) {
      ProductOptionResponse response = resultMap.computeIfAbsent(
          dto.getProductItemId(), id -> new ProductOptionResponse(
              id, dto.getStock(), new ArrayList<>()
          ));

      response.getOptions().add(new ProductOptionDto(
          dto.getOptionGroupId(),
          dto.getOptionGroupName(),
          dto.getOptionItemId(),
          dto.getOptionItemValue(),
          dto.getAdditionalPrice()
      ));
    }

    return new ArrayList<>(resultMap.values());
  }



}
