package com.sparta.commerce.domain.product.repository;

import com.sparta.commerce.domain.product.entity.OptionGroup;
import com.sparta.commerce.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {
  List<OptionGroup> findByProduct(Product product);
}
