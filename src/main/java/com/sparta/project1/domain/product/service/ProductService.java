package com.sparta.project1.domain.product.service;

import com.sparta.project1.domain.product.dto.ProductRequest;
import com.sparta.project1.domain.product.dto.ProductResponse;
import com.sparta.project1.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional(readOnly = true)
  public List<ProductResponse> getAll() {
    return new ArrayList<>();
  }

  @Transactional(readOnly = true)
  public ProductResponse getById(Long id) {
    return null;
  }

  @Transactional
  public ProductResponse create(ProductRequest request) {
    return null;
  }

  @Transactional
  public ProductResponse update(Long id, ProductRequest request) {
    return null;
  }

  @Transactional
  public void delete(Long id) {

  }
}
