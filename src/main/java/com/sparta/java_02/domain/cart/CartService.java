package com.sparta.java_02.domain.cart;

import com.sparta.java_02.domain.product.Product;
import com.sparta.java_02.domain.product.ProductRepository;
import com.sparta.java_02.domain.user.User;
import com.sparta.java_02.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /**
     * 장바구니에 상품 추가
     */
    @Transactional
    public Cart addToCart(Long userId, Long productId, Integer quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        // 재고 확인
        if (!product.isStockAvailable(quantity)) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        // 이미 장바구니에 있는 상품인지 확인
        return cartRepository.findByUserIdAndProductId(userId, productId)
                .map(existingCart -> {
                    // 기존 수량에 추가
                    existingCart.increaseQuantity(quantity);
                    return cartRepository.save(existingCart);
                })
                .orElseGet(() -> {
                    // 새로운 장바구니 항목 생성
                    Cart newCart = new Cart(user, product, quantity);
                    return cartRepository.save(newCart);
                });
    }

    /**
     * 사용자의 장바구니 목록 조회
     */
    public List<Cart> getCartItems(Long userId) {
        return cartRepository.findByUserIdWithProduct(userId);
    }

    /**
     * 장바구니 상품 수량 변경
     */
    @Transactional
    public Cart updateCartQuantity(Long userId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니에서 해당 상품을 찾을 수 없습니다."));

        // 재고 확인
        if (!cart.getProduct().isStockAvailable(quantity)) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        cart.updateQuantity(quantity);
        return cartRepository.save(cart);
    }

    /**
     * 장바구니에서 상품 삭제
     */
    @Transactional
    public void removeFromCart(Long userId, Long productId) {
        if (!cartRepository.findByUserIdAndProductId(userId, productId).isPresent()) {
            throw new IllegalArgumentException("장바구니에서 해당 상품을 찾을 수 없습니다.");
        }
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    /**
     * 사용자의 모든 장바구니 항목 삭제
     */
    @Transactional
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    /**
     * 장바구니 항목 개수 조회
     */
    public long getCartItemCount(Long userId) {
        return cartRepository.countByUserId(userId);
    }
} 