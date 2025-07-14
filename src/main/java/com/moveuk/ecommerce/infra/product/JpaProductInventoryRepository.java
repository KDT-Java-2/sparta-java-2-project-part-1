package com.moveuk.ecommerce.infra.product;

import com.moveuk.ecommerce.domain.product.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
}
