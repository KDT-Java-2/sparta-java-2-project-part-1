package com.dogworld.dogdog.product.infrastructure;

import com.dogworld.dogdog.product.interfaces.dto.request.ProductSearchCondition;
import com.dogworld.dogdog.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {
  Page<Product> search(ProductSearchCondition condition, Pageable pageable);
  boolean isIncludeInCompletedOrder(Long productId);
}
