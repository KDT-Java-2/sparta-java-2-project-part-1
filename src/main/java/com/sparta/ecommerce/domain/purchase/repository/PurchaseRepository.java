package com.sparta.ecommerce.domain.purchase.repository;

import com.sparta.ecommerce.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
  // JpaRepository provides methods for CRUD operations, so no additional methods are needed here.
  // You can define custom query methods if necessary, but for basic operations, this is sufficient.

}
