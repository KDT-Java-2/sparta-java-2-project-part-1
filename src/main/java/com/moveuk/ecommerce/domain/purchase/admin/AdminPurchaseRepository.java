package com.moveuk.ecommerce.domain.purchase.admin;

import com.moveuk.ecommerce.domain.purchase.PurchaseItem;

import java.util.Optional;

public interface AdminPurchaseRepository {
    Optional<PurchaseItem> findById(Long id);
}
