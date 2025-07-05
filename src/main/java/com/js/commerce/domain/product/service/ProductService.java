package com.js.commerce.domain.product.service;

import com.js.commerce.common.exception.ServiceException;
import com.js.commerce.common.exception.ServiceExceptionCode;
import com.js.commerce.domain.product.dto.ProductDetailResponse;
import com.js.commerce.domain.product.dto.ProductSearchCondition;
import com.js.commerce.domain.product.dto.ProductSearchPagedResponse;
import com.js.commerce.domain.product.entity.Product;
import com.js.commerce.domain.product.mapper.ProductDetailMapper;
import com.js.commerce.domain.product.mapper.ProductPagedMapper;
import com.js.commerce.domain.product.repository.ProductQueryRepository;
import com.js.commerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductQueryRepository queryRepository;
  private final ProductPagedMapper productPagedMapper;
  private final ProductDetailMapper detailMapper;

  public ProductSearchPagedResponse searchProducts(ProductSearchCondition condition) {

    Pageable pageable = PageRequest.of(condition.getPage(), condition.getSize());
    Page<Product> page = queryRepository.search(condition, pageable);

    return productPagedMapper.toPagedResponse(page);
  }

  public ProductDetailResponse getProductDetail(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
    return detailMapper.toResponse(product);
  }

}
