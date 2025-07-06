package com.moveuk.ecommerce.infra.purchase.admin;

import com.moveuk.ecommerce.domain.purchase.PurchaseItem;
import com.moveuk.ecommerce.domain.purchase.admin.AdminPurchaseRepository;
import com.moveuk.ecommerce.infra.purchase.JpaPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IAdminPurchaseRepository implements AdminPurchaseRepository {

    private final JpaPurchaseRepository jpaPurchaseRepository;

    @Override
    public Optional<PurchaseItem> findById(Long id) {
        return jpaPurchaseRepository.findPurchaseItemByProductId(id);
    }
}
