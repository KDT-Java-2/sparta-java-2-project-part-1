package com.sparta.commerce.domain.product.service;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.common.exception.ServiceExceptionCode;
import com.sparta.commerce.domain.product.dto.ProductDetailResponse;
import com.sparta.commerce.domain.product.dto.ProductFindRequest;
import com.sparta.commerce.domain.product.dto.ProductFindResponse;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.mapper.ProductMapper;
import com.sparta.commerce.domain.product.repository.ProductQueryRepository;
import com.sparta.commerce.domain.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductQueryRepository productQueryRepository;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public Page<ProductFindResponse> findSearchProducts(ProductFindRequest request) {
    int size = request.getSize() != null ? request.getSize() : 10; // 기본값 10
    int page = request.getPage() != null ? request.getPage() - 1 : 0; // 기본값 0

    Pageable pageable = Pageable.ofSize(size).withPage(page);

    return productQueryRepository.findSearchProducts(
        request,
        pageable
    );

  }

  public ProductDetailResponse findProductById(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));

    return productMapper.toProductDetailResponse(product);
  }

}
