package com.sparta.commerceProject.domain.purchaseItem.repository;

import com.sparta.commerceProject.domain.purchaseItem.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

}
