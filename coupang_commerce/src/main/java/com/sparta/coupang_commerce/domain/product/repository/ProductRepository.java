package com.sparta.coupang_commerce.domain.product.repository;

import com.sparta.coupang_commerce.domain.product.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE p.id = :id")
  Optional<Product> findProductById(Long id);
}
