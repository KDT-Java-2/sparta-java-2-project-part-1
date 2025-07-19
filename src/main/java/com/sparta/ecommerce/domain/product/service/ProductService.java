package com.sparta.ecommerce.domain.product.service;

import com.sparta.ecommerce.common.exception.ServiceException;
import com.sparta.ecommerce.common.exception.ServiceExceptionCode;
import com.sparta.ecommerce.domain.product.dto.ProductInfoResponse;
import com.sparta.ecommerce.domain.product.dto.ProductListResponse;
import com.sparta.ecommerce.domain.product.dto.ProductRequest;
import com.sparta.ecommerce.domain.product.dto.ProductSearchRequest;
import com.sparta.ecommerce.domain.product.entity.Product;
import com.sparta.ecommerce.domain.product.mapper.ProductMapper;
import com.sparta.ecommerce.domain.product.repository.ProductQueryRepository;
import com.sparta.ecommerce.domain.product.repository.ProductRepository;
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

  public Page<ProductListResponse> getProductPageList(ProductSearchRequest request,
      Pageable pageable) {
    return productQueryRepository.findProductList(request, pageable);
  }

  public ProductInfoResponse getById(Long id) {
    Product product = productRepository.findWithCategoryById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    return productMapper.toProductResponse(product);
  }

  public ProductInfoResponse create(ProductRequest request) {
    return null;
  }

  public ProductInfoResponse update(Long id, ProductRequest request) {
    return null;
  }

  public void delete(Long id) {

  }
}
