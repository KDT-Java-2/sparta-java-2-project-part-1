package com.java_project.part_1.domain.product.service;

import com.java_project.part_1.domain.product.dto.ProductSearchResponse;
import com.java_project.part_1.domain.product.mapper.ProductMapper;
import com.java_project.part_1.domain.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;

  private final ProductQueryRepository productQueryRepository;

  public Page<ProductSearchResponse> searchProduct(Long categoryId, Integer minPrice,
      Integer maxPrice, Pageable pageable) {
    return productQueryRepository
        .findProducts(categoryId, minPrice, maxPrice, pageable)
        .map(productMapper::toResponse)
        ;
  }
}
