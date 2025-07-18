package com.socialcommerce.domain.product.repository;

import com.socialcommerce.domain.product.dto.ProductResponse;
import com.socialcommerce.domain.product.entity.Product;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  // PK(id)로 검색 (JpaRepository가 이미 제공)
  @NotNull Optional<Product> findById(@NotNull Long id);
}
