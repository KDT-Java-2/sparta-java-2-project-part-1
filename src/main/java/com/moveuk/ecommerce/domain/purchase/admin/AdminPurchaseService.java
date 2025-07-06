package com.moveuk.ecommerce.domain.purchase.admin;

import com.moveuk.ecommerce.domain.purchase.PurchaseItem;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPurchaseService {

    private final AdminPurchaseRepository adminPurchaseRepository;

    public PurchaseItem findById(Long id) {
        return adminPurchaseRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Purchase item not found"));
    }
}
