package com.sparta.commerceProject.domain.purchase.repository;

import com.sparta.commerceProject.domain.purchaseItem.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseItem, Long> {

}
