package com.sparta.commerce_project_01.domain.cart.repository;

import com.sparta.commerce_project_01.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
