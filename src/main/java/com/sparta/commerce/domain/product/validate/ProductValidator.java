package com.sparta.commerce.domain.product.validate;

import static com.sparta.commerce.common.exception.ServiceExceptionCode.NOT_FOUND_CATEGORY;
import static com.sparta.commerce.common.exception.ServiceExceptionCode.NOT_FOUND_PRODUCT;
import static com.sparta.commerce.common.exception.ServiceExceptionCode.NOT_FOUND_PURCHASE_PRODUCT;
import static com.sparta.commerce.common.exception.ServiceExceptionCode.PRICE_CANNOT_BE_NEGATIVE;

import com.sparta.commerce.common.enums.PurchaseStatus;
import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.category.repository.CategoryRepository;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.entity.ProductItem;
import com.sparta.commerce.domain.product.repository.ProductItemRepository;
import com.sparta.commerce.domain.product.repository.ProductRepository;
import com.sparta.commerce.domain.purchase.repository.PurchaseProductRepository;
import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductValidator {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final ProductItemRepository productItemRepository;
  private final PurchaseProductRepository purchaseProductRepository;

  public Product getProduct(Long productId){
    return productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(NOT_FOUND_PRODUCT));
  }

  public Category validateAndGetCategory(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(NOT_FOUND_CATEGORY));
  }

  public void validatePrice(BigDecimal price) {
    if (price.compareTo(BigDecimal.ZERO) < 0) {
      throw new ServiceException(PRICE_CANNOT_BE_NEGATIVE);
    }
  }

  public void validateDeletable(Product product){
    EnumSet<PurchaseStatus> deletableStatuses = PurchaseStatus.getDeletableStatuses();

    List<ProductItem> productItems = productItemRepository.findByProduct(product);
    for (ProductItem productItem : productItems) {
      purchaseProductRepository.findByProductItem(productItem)
          .filter(p -> deletableStatuses.contains(p.getStatus()))
          .ifPresent(p -> {
            throw new ServiceException(NOT_FOUND_PURCHASE_PRODUCT);
          });
    }
  }
}
