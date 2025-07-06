package com.sparta.commerce_project_01.domain.product.service;

import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import com.sparta.commerce_project_01.common.enums.exception.ServiceExceptionCode;
import com.sparta.commerce_project_01.domain.category.entity.Category;
import com.sparta.commerce_project_01.domain.category.repository.CategoryRepository;
import com.sparta.commerce_project_01.domain.product.dto.ProductRequest;
import com.sparta.commerce_project_01.domain.product.dto.ProductResponse;
import com.sparta.commerce_project_01.domain.product.entity.Product;
import com.sparta.commerce_project_01.domain.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Transactional
  public void save(ProductRequest request) {
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND));

    productRepository.save(Product.builder()
        .category(category)
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .stock(request.getStock())
        .build());
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
        .createdAt(product.getCreatedAt())
        .build();
  }

  public Void delete(Long productId) {
    if (ObjectUtils.isEmpty(productId)) {
      // 예외 발생전에 필요한 조치 할 수 있는 영역
      throw new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND);
    }
    productRepository.deleteById(productId);

    // ??
    return null;
  }

  public ProductResponse update(Long productId, ProductRequest productRequest) {
    return null;
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
            .createdAt(product.getCreatedAt())
            .build()))
        .toList();
  }
}
