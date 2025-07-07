package com.sparta.bootcamp.shop.domain.purchase.repository;

import java.util.Optional;

import com.sparta.bootcamp.shop.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  Optional<Purchase> findByIdAndUser_Id(Long id, Long userId);

}