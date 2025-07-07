package com.sparta.commerce.domain.product.service;

import static com.sparta.commerce.common.exception.ServiceExceptionCode.INVALID_REQUEST;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.domain.product.dto.option.ProductOptionDto;
import com.sparta.commerce.domain.product.dto.option.ProductOptionRequest;
import com.sparta.commerce.domain.product.dto.option.ProductOptionRequest.OptionValueAndAddPrice;
import com.sparta.commerce.domain.product.dto.option.ProductOptionResponse;
import com.sparta.commerce.domain.product.dto.product.productItem.ProductItemRequest;
import com.sparta.commerce.domain.product.entity.OptionGroup;
import com.sparta.commerce.domain.product.entity.OptionItem;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.entity.ProductItem;
import com.sparta.commerce.domain.product.entity.ProductItemOption;
import com.sparta.commerce.domain.product.repository.OptionGroupRepository;
import com.sparta.commerce.domain.product.repository.OptionItemRepository;
import com.sparta.commerce.domain.product.repository.ProductItemOptionRepository;
import com.sparta.commerce.domain.product.repository.ProductItemRepository;
import com.sparta.commerce.domain.product.validate.OptionValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionService {
  private final OptionGroupRepository optionGroupRepository;
  private final OptionItemRepository optionItemRepository;
  private final ProductItemRepository productItemRepository;
  private final ProductItemOptionRepository productItemOptionRepository;

  private final OptionValidator optionValidator;

  public List<ProductOptionResponse> saveOptions(Product product, List<ProductOptionRequest> optionGroups, List<ProductItemRequest> productItems) {
    Map<String, OptionItem> optionItemMap = saveOptionGroupsAndItems(product, optionGroups);
    List<ProductOptionResponse> options = saveProductItemsWithOptions(product, productItems, optionItemMap);
    return options;
  }

  public void deleteOptions(Product product){
    List<ProductItem> productItems = productItemRepository.findByProduct(product);
    for (ProductItem item : productItems) {
      productItemOptionRepository.deleteByProductItem(item);
    }
    productItemRepository.deleteAll(productItems);

    List<OptionGroup> optionGroups = optionGroupRepository.findByProduct(product);
    for (OptionGroup optionGroup : optionGroups) {
      List<OptionItem> optionItems = optionItemRepository.findByOptionGroup(optionGroup);
      optionItemRepository.deleteAll(optionItems);
    }
    optionGroupRepository.deleteAll(optionGroups);
  }

  private Map<String, OptionItem> saveOptionGroupsAndItems(Product product, List<ProductOptionRequest> optionGroups){
    Map<String, OptionItem> optionItemMap = new HashMap<>();
    for (ProductOptionRequest optionRequest : optionGroups) {
      OptionGroup optionGroup = new OptionGroup(product, optionRequest.getKey());
      optionGroupRepository.save(optionGroup);

      for (OptionValueAndAddPrice optionValue : optionRequest.getValues()) {
        OptionItem item = createOptionItem(optionGroup, optionValue);
        optionItemRepository.save(item);
        String optionCombinationKey = generateOptionKey(optionGroup.getName(), optionValue.getItem());
        optionItemMap.put(optionCombinationKey, item);
      }
    }
    return optionItemMap;
  }

  private List<ProductOptionResponse> saveProductItemsWithOptions(Product product, List<ProductItemRequest> productItems, Map<String, OptionItem> optionItemMap){
    List<ProductOptionResponse> responses = new ArrayList<>();

    for (ProductItemRequest productItemRequest : productItems) {
      optionValidator.validateStock(productItemRequest.getStock());

      ProductItem productItem = createProductItem(product, productItemRequest.getStock());
      productItemRepository.save(productItem);

      List<ProductOptionDto> optionValues = new ArrayList<>();
      for (Map.Entry<String, String> entry : productItemRequest.getOptionCombination().entrySet()) {
        String optionGroupName = entry.getKey();
        String optionValue = entry.getValue();
        String optionCombinationKey = generateOptionKey(optionGroupName, optionValue);

        OptionItem optionItem = optionItemMap.get(optionCombinationKey);
        if (optionItem == null) {
          throw new ServiceException(INVALID_REQUEST);
        }
        ProductItemOption itemOption = createProductItemOption(productItem, optionItem);
        productItemOptionRepository.save(itemOption);
        optionValues.add(ProductOptionDto.of(optionItem));
      }
      responses.add(ProductOptionResponse.of(productItem.getId(), productItem.getStock(), optionValues));
    }
    return responses;
  }

  private OptionItem createOptionItem(OptionGroup optionGroup, OptionValueAndAddPrice optionValueAndAddPrice){
    return OptionItem.builder()
        .optionGroup(optionGroup)
        .value(optionValueAndAddPrice.getItem())
        .additionalPrice(optionValueAndAddPrice.getAdditionalPrice())
        .build();
  }

  private ProductItem createProductItem(Product product, int stock){
    return ProductItem.builder()
        .product(product)
        .stock(stock)
        .build();
  }

  private ProductItemOption createProductItemOption(ProductItem productItem, OptionItem optionItem){
    return  ProductItemOption.builder()
        .productItem(productItem)
        .optionItem(optionItem)
        .build();
  }

  private String generateOptionKey(String groupName, String itemName) {
    return groupName + ":" + itemName;
  }
}
