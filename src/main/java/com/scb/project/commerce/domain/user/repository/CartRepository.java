package com.scb.project.commerce.domain.user.repository;

import com.scb.project.commerce.domain.user.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
