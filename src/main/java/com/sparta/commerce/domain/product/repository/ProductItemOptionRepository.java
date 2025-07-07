package com.sparta.commerce.domain.product.repository;

import com.sparta.commerce.domain.product.entity.ProductItem;
import com.sparta.commerce.domain.product.entity.ProductItemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemOptionRepository extends JpaRepository<ProductItemOption, Long> {
  @Modifying
  @Query("DELETE FROM ProductItemOption p WHERE p.productItem = :productItem")
  void deleteByProductItem(ProductItem productItem);

}
