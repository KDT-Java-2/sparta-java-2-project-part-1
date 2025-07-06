package com.sparta.commerce_project_01.domain.purchase.repository;

import com.sparta.commerce_project_01.domain.purchase.entity.Purchase;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  Optional<Purchase> findByIdAndUser_Id(Long id, Long userId);

}





