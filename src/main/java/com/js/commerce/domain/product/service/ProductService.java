package com.js.commerce.domain.product.service;

import com.js.commerce.domain.product.dto.ProductSearchCondition;
import com.js.commerce.domain.product.dto.ProductSearchPagedResponse;
import com.js.commerce.domain.product.entity.Product;
import com.js.commerce.domain.product.mapper.ProductPagedMapper;
import com.js.commerce.domain.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductQueryRepository queryRepository;
  private final ProductPagedMapper productPagedMapper;

  public ProductSearchPagedResponse searchProducts(ProductSearchCondition condition) {

    Pageable pageable = PageRequest.of(condition.getPage(), condition.getSize());
    Page<Product> page = queryRepository.search(condition, pageable);

    return productPagedMapper.toPagedResponse(page);
  }

}
