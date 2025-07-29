package com.sparta.commerce_project_01.domain.product.service;

import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import com.sparta.commerce_project_01.common.enums.exception.ServiceExceptionCode;
import com.sparta.commerce_project_01.domain.category.entity.Category;
import com.sparta.commerce_project_01.domain.category.repository.CategoryRepository;
import com.sparta.commerce_project_01.domain.product.dto.ProductRequest;
import com.sparta.commerce_project_01.domain.product.dto.ProductResponse;
import com.sparta.commerce_project_01.domain.product.dto.ProductSearchResponse;
import com.sparta.commerce_project_01.domain.product.entity.Product;
import com.sparta.commerce_project_01.domain.product.mapper.ProductMapper;
import com.sparta.commerce_project_01.domain.product.repository.ProductQueryRepository;
import com.sparta.commerce_project_01.domain.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductQueryRepository productQueryRepository;
  private final ProductMapper mapper;
  private final CategoryRepository categoryRepository;

  @Transactional
  public ProductResponse save(ProductRequest request) {
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND));

    Product product = productRepository.save(Product.builder()
        .category(category)
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .stock(request.getStock())
        .build());

    return ProductResponse.builder()
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .categoryId(product.getCategory().getId())
        .build();
  }

  @Transactional
  public ProductResponse getById(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND));

    return ProductResponse.builder()
        .id(product.getId())
        .categoryId(product.getCategory().getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .build();
  }

  public void delete(Long productId) {
    Product product = getProductById(productId);

    productRepository.delete(product);

  }

  public ProductResponse update(Long productId, ProductRequest request) {
    Product product = getProductById(productId);
    Category category = categoryRepository.getCategoryById(request.getCategoryId());

    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());
    product.setCategory(category);

    productRepository.save(product);

    return ProductResponse.builder()
        .id(product.getId())
        .categoryId(product.getCategory().getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .build();
  }

  @Transactional
  public List<ProductResponse> getAll() {
    return productRepository.findAll().stream()
        .map((product -> ProductResponse.builder()
            .id(product.getId())
            .categoryId(product.getCategory().getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .stock(product.getStock())
            .build()))
        .toList();
  }

  Product getProductById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND));
  }

  void validateStock(Product product, int requestedQuantity) {
    if (requestedQuantity > product.getStock()) {
      throw new ServiceException(ServiceExceptionCode.PRODUCT_OUT_OF_STOCK);
    }
  }

  @Transactional(readOnly = true)
  public Page<ProductSearchResponse> findAll(
      Long categoryId,
      Integer minPrice,
      Integer maxPrice,
      Pageable pageable
  ) {
    return productQueryRepository.findProducts(categoryId, minPrice, maxPrice, pageable).map(
        mapper::toResponse
    );
  }
}
