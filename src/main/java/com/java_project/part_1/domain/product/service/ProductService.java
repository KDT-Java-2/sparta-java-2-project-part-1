package com.java_project.part_1.domain.product.service;

import com.java_project.part_1.common.exception.ServiceException;
import com.java_project.part_1.common.exception.ServiceExceptionCode;
import com.java_project.part_1.domain.product.dto.ProductSearchResponse;
import com.java_project.part_1.domain.product.entity.Product;
import com.java_project.part_1.domain.product.mapper.ProductMapper;
import com.java_project.part_1.domain.product.repository.ProductQueryRepository;
import com.java_project.part_1.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;

  private final ProductQueryRepository productQueryRepository;

  @Transactional
  public Page<ProductSearchResponse> searchProduct(Long categoryId, Integer minPrice,
      Integer maxPrice, Pageable pageable) {
    return productQueryRepository
        .findProducts(categoryId, minPrice, maxPrice, pageable)
        .map(productMapper::toResponse)
        ;
  }

  @Transactional
  public ProductSearchResponse getProductDetail(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    return productMapper.toResponse(product);
  }
}
