package com.socialcommerce.domain.cart.repository;

import com.socialcommerce.domain.cart.entity.Cart;
import com.socialcommerce.domain.cart.entity.CartItem;
import com.socialcommerce.domain.product.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
  Optional<CartItem> findByCartId(Long cartId);
  void deleteByCartIdAndProductId(Long userId, Long productId);
//  Optional<CartItem> findByCartAndProductId(Cart cart, Long productId);
}
