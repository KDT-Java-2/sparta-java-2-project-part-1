package com.socialcommerce.domain.product.repository;

import com.socialcommerce.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {
  Page<Product> searchProducts(
      Long category,
      Integer minPrice,
      Integer maxPrice,
      Pageable pageable
  );

}
