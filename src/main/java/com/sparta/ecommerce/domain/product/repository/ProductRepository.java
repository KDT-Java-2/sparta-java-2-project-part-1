package com.sparta.ecommerce.domain.product.repository;

import com.sparta.ecommerce.domain.product.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
  Optional<Product> findWithCategoryById(@Param("id") Long id);

  Optional<Product> findByProductNm(String name);
  
  boolean existsByCategoryId(Long categoryId);
}
