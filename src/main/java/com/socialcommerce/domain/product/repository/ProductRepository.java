package com.socialcommerce.domain.product.repository;

import com.socialcommerce.domain.product.dto.ProductResponse;
import com.socialcommerce.domain.product.entity.Product;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findById(@NotNull Long id);

  Optional<Product> findByName(@NotNull String name);
}
