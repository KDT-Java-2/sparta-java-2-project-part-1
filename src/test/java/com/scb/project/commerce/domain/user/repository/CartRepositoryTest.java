package com.scb.project.commerce.domain.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.project.commerce.domain.product.entity.Product;
import com.scb.project.commerce.domain.product.repository.ProductRepository;
import com.scb.project.commerce.domain.user.entity.Cart;
import com.scb.project.commerce.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("기본 CRUD : 장바구니 조회에 성공합니다.")
    void selectCartSuccess() {
        // when
        Cart cart = cartRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("장바구니가 존재하지 않습니다."));

        // then
        assertThat(cart.getUser().getId()).isEqualTo(12);
    }

    @Test
    @DisplayName("기본 CRUD : 장바구니 생성에 성공합니다.")
    void createCartSuccess() {
        // given
        User user = userRepository.findById(12L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        Product product = productRepository.findById(4L)
            .orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));

        // when
        Cart saveCart = Cart.builder()
            .user(user)
            .product(product)
            .quantity(1)
            .build();

        cartRepository.save(saveCart);

        // then
        List<Cart> cart = cartRepository.findAllByUser(user);

        assertThat(saveCart).isIn(cart);
    }

    @Test
    @DisplayName("기본 CRUD : 장바구니 삭제에 성공합니다.")
    void deleteCartSuccess() {
        // given
        User user = userRepository.findById(12L)
            .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        Product product = productRepository.findById(4L)
            .orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));

        // when
        cartRepository.deleteByUserAndProduct(user, product);

        // when
        boolean isExists = cartRepository.existsByUserAndProduct(user, product);

        assertThat(isExists).isFalse();
    }
}