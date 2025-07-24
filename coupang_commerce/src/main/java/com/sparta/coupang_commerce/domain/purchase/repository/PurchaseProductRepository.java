package com.sparta.coupang_commerce.domain.purchase.repository;

import com.sparta.coupang_commerce.domain.purchase.entity.PurchaseProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

  List<PurchaseProduct> findByPurchaseId(Long purchaseId);

}
