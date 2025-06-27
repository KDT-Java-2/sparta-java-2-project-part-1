package com.sparta.dev.java_02_project.domain.cart.repository;

import com.sparta.dev.java_02_project.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
