package com.dogworld.dogdog.domain.product.repository;

import com.dogworld.dogdog.api.product.request.ProductSearchCondition;
import com.dogworld.dogdog.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {
  Page<Product> search(ProductSearchCondition condition, Pageable pageable);
  boolean isIncludeInCompletedOrder(Long productId);
}
