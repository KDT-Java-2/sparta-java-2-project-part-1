package com.sparta.commerce.domain.product.service;

import com.sparta.commerce.common.enums.PurchaseStatus;
import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.common.exception.ServiceExceptionCode;
import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.category.repository.CategoryRepository;
import com.sparta.commerce.domain.product.dto.ProductDetailResponse;
import com.sparta.commerce.domain.product.dto.ProductSearchRequest;
import com.sparta.commerce.domain.product.dto.ProductSearchResponse;
import com.sparta.commerce.domain.product.dto.admin.ProductCreateRequest;
import com.sparta.commerce.domain.product.dto.admin.ProductResponse;
import com.sparta.commerce.domain.product.dto.admin.ProductUpdateRequest;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.mapper.ProductMapper;
import com.sparta.commerce.domain.product.repository.ProductQueryRepository;
import com.sparta.commerce.domain.product.repository.ProductRepository;
import com.sparta.commerce.domain.purchase.repository.PurchaseProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductQueryRepository productQueryRepository;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  private final CategoryRepository categoryRepository;
  private final PurchaseProductRepository purchaseProductRepository;

  @Transactional(readOnly = true)
  public Page<ProductSearchResponse> searchProducts(ProductSearchRequest productSearchRequest, Pageable pageable) {
    return productQueryRepository.searchAll(productSearchRequest, pageable);
  }

  @Transactional(readOnly = true)
  public ProductDetailResponse findProductDetail(Long productId) {
    Product product = productRepository.findByProductIdWithCategory(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));

    return productMapper.toDetailResponse(product);
  }

  @Transactional
  public ProductResponse createProduct(ProductCreateRequest request) {
    validateProductName(request.getName());
    Category category = validateCategoryId(request.getCategoryId());

    Product product = Product.builder()
        .name(request.getName())
        .category(category)
        .price(request.getPrice())
        .stock(request.getStock())
        .description(request.getDescription())
        .isActive(request.getIsActive())
        .build();

    productRepository.save(product);

    return productMapper.toResponse(product);
  }

  @Transactional
  public ProductResponse updateProduct(Long productId, ProductUpdateRequest request) {
    Product product = findProduct(productId);

    validateProductNameForUpdate(productId, request.getName());
    Category category = validateCategoryId(request.getCategoryId());

    product.updateProduct(
        category,
        request.getName(),
        request.getDescription(),
        request.getPrice(),
        request.getStock(),
        request.getIsActive()
    );

    return productMapper.toResponse(product);
  }

  @Transactional
  public void deleteProduct(Long productId) {
    Product product = findProduct(productId);

    if (purchaseProductRepository.existsByProductIdAndPurchaseStatus(productId, PurchaseStatus.COMPLETED)) {
      throw new ServiceException(ServiceExceptionCode.PRODUCT_IN_COMPLETED_PURCHASE);
    };

    productRepository.delete(product);
  }

  private Product findProduct(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));
  }

  private void validateProductName(String name) {
    Optional<Product> existingProduct = productRepository.findByName(name);
    if (existingProduct.isPresent()) {
      throw new ServiceException(ServiceExceptionCode.ALREADY_EXISTS_PRODUCT_NAME);
    }
  }

  private void validateProductNameForUpdate(Long productId, String name) {
    Optional<Product> existingProduct = productRepository.findByName(name);
    if (existingProduct.isPresent() && !existingProduct.get().getId().equals(productId)) {
      throw new ServiceException(ServiceExceptionCode.ALREADY_EXISTS_PRODUCT_NAME);
    }
  }
  private Category validateCategoryId(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
  }

}
