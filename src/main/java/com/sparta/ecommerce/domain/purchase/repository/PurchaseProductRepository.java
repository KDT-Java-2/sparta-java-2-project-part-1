package com.sparta.ecommerce.domain.purchase.repository;

import com.sparta.ecommerce.domain.purchase.entity.PurchaseProdcut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProdcut, Long> {

}
