package com.sparta.commerce.domain.product.repository;

import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.entity.ProductItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
  List<ProductItem> findByProduct(Product product);

}
