package com.sparta.part_1.domain.product.service;


import com.sparta.part_1.domain.product.dto.request.ProductSearchRequest;
import com.sparta.part_1.domain.product.dto.response.ProductResponse;
import com.sparta.part_1.domain.product.repository.ProductQueryRepository;
import com.sparta.part_1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductQueryRepository productQueryRepository;

  public Page<ProductResponse> findProducts(ProductSearchRequest request, Pageable pageable) {
    return productQueryRepository.searchProductsPage(request, pageable);
  }
}
