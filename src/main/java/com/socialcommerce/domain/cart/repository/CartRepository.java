package com.socialcommerce.domain.cart.repository;

import com.socialcommerce.domain.cart.entity.Cart;
import com.socialcommerce.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  Optional<Cart> findByUser(User user);
}
