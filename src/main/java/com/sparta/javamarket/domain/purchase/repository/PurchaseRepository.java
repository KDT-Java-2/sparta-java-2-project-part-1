package com.sparta.javamarket.domain.purchase.repository;

import com.sparta.javamarket.domain.purchase.entity.Purchase;
import com.sparta.javamarket.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {


}
