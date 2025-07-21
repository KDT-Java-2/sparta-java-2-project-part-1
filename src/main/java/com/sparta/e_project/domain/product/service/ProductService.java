package com.sparta.e_project.domain.product.service;

import com.sparta.e_project.domain.product.dto.ProductRequest;
import com.sparta.e_project.domain.product.dto.ProductResponse;
import com.sparta.e_project.domain.product.dto.ProductSearchResponse;
import com.sparta.e_project.domain.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductQueryRepository productQueryRepository;


  // 상품 조회 메서드
  @Transactional
  public Page<ProductSearchResponse> getAllProducts(ProductRequest request, Pageable pageable) {
    return productQueryRepository.findPagedProducts(request, pageable);
  }

  @Transactional
  public ProductResponse getProductById(Long productId) {
    return productQueryRepository.findProductById(productId);
  }

}
