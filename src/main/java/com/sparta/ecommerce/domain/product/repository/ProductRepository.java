package com.sparta.ecommerce.domain.product.repository;

import com.sparta.ecommerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  // JpaRepository provides methods for CRUD operations, so no additional methods are needed here.
  // You can define custom query methods if necessary, but for basic operations, this is sufficient.

}
