package com.sparta.domain.cart.repository;

import com.sparta.domain.cart.entity.Cart;
import com.sparta.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


}
