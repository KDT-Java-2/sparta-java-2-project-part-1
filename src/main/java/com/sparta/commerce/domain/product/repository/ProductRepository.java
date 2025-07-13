package com.sparta.commerce.domain.product.repository;

import com.sparta.commerce.domain.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findByName(@NotBlank String name);

  @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
  Optional<Product> findByProductIdWithCategory(Long id);

}
