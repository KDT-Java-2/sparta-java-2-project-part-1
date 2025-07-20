package com.sparta.commerce.domain.purchase.repository;

import com.sparta.commerce.common.enums.PurchaseStatus;
import com.sparta.commerce.domain.product.entity.ProductItem;
import com.sparta.commerce.domain.product.service.OptionService;
import com.sparta.commerce.domain.purchase.entity.PurchaseProduct;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {
  Optional<PurchaseProduct> findByProductItem(ProductItem productItem);

}
