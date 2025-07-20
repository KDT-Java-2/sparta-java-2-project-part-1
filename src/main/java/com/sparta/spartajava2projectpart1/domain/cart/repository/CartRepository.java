package com.sparta.spartajava2projectpart1.domain.cart.repository;

import com.sparta.spartajava2projectpart1.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
