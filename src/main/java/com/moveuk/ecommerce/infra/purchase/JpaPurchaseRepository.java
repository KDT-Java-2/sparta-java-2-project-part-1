package com.moveuk.ecommerce.infra.purchase;

import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.purchase.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPurchaseRepository extends JpaRepository<PurchaseItem, Long> {
    Optional<PurchaseItem> findPurchaseItemByProductId(Long id);

    boolean existsByProductAndPurchase_Status(Product product, String completed);
}
