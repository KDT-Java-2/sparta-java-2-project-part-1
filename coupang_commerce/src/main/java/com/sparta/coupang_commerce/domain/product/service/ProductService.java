package com.sparta.coupang_commerce.domain.product.service;

import com.sparta.coupang_commerce.common.exception.ServiceException;
import com.sparta.coupang_commerce.common.exception.ServiceExceptionCode;
import com.sparta.coupang_commerce.domain.product.dto.ProductCategoryResponse;
import com.sparta.coupang_commerce.domain.product.dto.ProductPageRequest;
import com.sparta.coupang_commerce.domain.product.dto.ProductPageResponse;
import com.sparta.coupang_commerce.domain.product.dto.ProductResponse;
import com.sparta.coupang_commerce.domain.product.entity.Product;
import com.sparta.coupang_commerce.domain.product.mapper.ProductMapper;
import com.sparta.coupang_commerce.domain.product.repository.ProductQueryRepository;
import com.sparta.coupang_commerce.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;
  private final ProductQueryRepository productQueryRepository;
  private final ProductRepository productRepository;

  @Transactional
  public Page<ProductPageResponse> getProductPageResponse(ProductPageRequest request, Pageable pageable) {
    Page<Product> products = productQueryRepository.findProducts(request.getCategoryId(), request.getMinPrice(), request.getMaxPrice(), pageable);

    return products.map(productMapper::toProductPageResponse);
  }

  @Transactional
  public ProductResponse getProductResponse(Long productId) {
    Product product = getProduct(productId);

    ProductCategoryResponse categoryResponse = productMapper.toProductCategoryResponse(product.getCategory());

    return productMapper.toProductResponse(product, categoryResponse);
  }

  private Product getProduct(Long productId) {
    return productRepository.findProductById(productId).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
  }

}
