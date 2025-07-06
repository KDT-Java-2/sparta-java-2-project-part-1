package com.sparta.e_project.domain.product.service;

import com.sparta.e_project.domain.product.dto.ProductRequest;
import com.sparta.e_project.domain.product.dto.ProductResponse;
import com.sparta.e_project.domain.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductQueryRepository productQueryRepository;

  // 상품 조회 메서드
  public Page<ProductResponse> findPagedProducts(ProductRequest request) {
    Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
    return productQueryRepository.findPagedProducts(request, pageable);
  }

}
