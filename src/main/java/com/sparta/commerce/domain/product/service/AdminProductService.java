package com.sparta.commerce.domain.product.service;

import com.sparta.commerce.common.enums.PurchaseStatus;
import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.common.exception.ServiceExceptionCode;
import com.sparta.commerce.domain.category.repository.CategoryRepository;
import com.sparta.commerce.domain.product.dto.ProductCreateRequest;
import com.sparta.commerce.domain.product.dto.ProductCreateResponse;
import com.sparta.commerce.domain.product.dto.ProductUpdateResponse;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.repository.ProductQueryRepository;
import com.sparta.commerce.domain.product.repository.ProductRepository;
import com.sparta.commerce.domain.purchase.PurchaseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProductService {
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final PurchaseQueryRepository purchaseQueryRepository;

  @Transactional
  public ProductCreateResponse createProduct(ProductCreateRequest request) {
    if(productRepository.existsByName(request.getName())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
    }

//    if(!categoryRepository.existsById(request.getCategoryId())) {
//      throw new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY);
//    }

    Product product = Product.builder()
        .name(request.getName())
        .price(request.getPrice())
        .stock(request.getStock())
        .category(categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY)))
        .build();

    Product savedProduct = productRepository.save(product);

    return ProductCreateResponse.builder()
        .productId(savedProduct.getId())
        .build();
  }

  @Transactional
  public ProductUpdateResponse updateProduct(ProductCreateRequest request, Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));

    if(request.getName() != null && !request.getName().equals(product.getName())) {
      if(productRepository.existsByName(request.getName())) {
        throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
      }
      product.setName(request.getName());
    }

    if(request.getPrice() != null && !request.getPrice().equals(product.getPrice())) {
      product.updatePrice(request.getPrice());
    }

    if(request.getStock() != null && !request.getStock().equals(product.getStock())) {
      product.updateStock(request.getStock());
    }

    if(request.getCategoryId() != null && !request.getCategoryId().equals(product.getCategory().getId())) {
      product.setCategory(categoryRepository.findById(request.getCategoryId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY)));
    }

    if(request.getImageUrl() != null && !request.getImageUrl().equals(product.getImageUrl())) {
      product.setImageUrl(request.getImageUrl());
    }

    if(request.getDescription() != null && !request.getDescription().equals(product.getDescription())) {
      product.setDescription(request.getDescription());
    }

    Product updatedProduct = productRepository.save(product);

    return ProductUpdateResponse.builder()
        .productId(updatedProduct.getId())
        .name(updatedProduct.getName())
        .price(updatedProduct.getPrice())
        .stock(updatedProduct.getStock())
        .categoryId(updatedProduct.getCategory().getId())
        .imageUrl(updatedProduct.getImageUrl())
        .description(updatedProduct.getDescription())
        .build();
  }

  @Transactional
  public void deleteProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));

    boolean hasOrders = purchaseQueryRepository.existsCompletedPurchaseProductForProduct(productId, PurchaseStatus.COMPLETED);
    if (hasOrders) {
      throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_PRODUCT_WITH_ORDERS);
    }

    // productRepository.delete(product);
  }

}
