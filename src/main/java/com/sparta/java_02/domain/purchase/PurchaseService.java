package com.sparta.java_02.domain.purchase;

import com.sparta.java_02.domain.cart.Cart;
import com.sparta.java_02.domain.cart.CartRepository;
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
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    /**
     * 단일 상품 구매
     */
    @Transactional
    public Purchase purchaseProduct(Long userId, Long productId, Integer quantity, 
                                   String paymentMethod, String deliveryAddress, String orderNotes) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        // 재고 확인 및 차감
        if (!product.isStockAvailable(quantity)) {
            throw new IllegalArgumentException("재고가 부족합니다. 현재 재고: " + product.getStockQuantity());
        }
        
        product.decreaseStock(quantity);
        productRepository.save(product);

        // 구매 생성
        Purchase purchase = new Purchase(user, product, quantity, product.getPrice(), 
                                       paymentMethod, deliveryAddress, orderNotes);
        
        return purchaseRepository.save(purchase);
    }

    /**
     * 장바구니에서 구매 (전체 장바구니)
     */
    @Transactional
    public List<Purchase> purchaseFromCart(Long userId, String paymentMethod, String deliveryAddress) {
        List<Cart> cartItems = cartRepository.findByUserIdWithProduct(userId);
        
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("장바구니가 비어있습니다.");
        }

        return cartItems.stream()
                .map(cart -> {
                    Purchase purchase = purchaseProduct(
                        userId, 
                        cart.getProduct().getId(), 
                        cart.getQuantity(),
                        paymentMethod, 
                        deliveryAddress, 
                        null
                    );
                    
                    // 장바구니에서 제거
                    cartRepository.delete(cart);
                    return purchase;
                })
                .toList();
    }

    /**
     * 구매 상태 변경
     */
    @Transactional
    public Purchase updatePurchaseStatus(Long purchaseId, PurchaseStatus newStatus) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalArgumentException("구매 내역을 찾을 수 없습니다."));

        purchase.updateStatus(newStatus);
        return purchaseRepository.save(purchase);
    }

    /**
     * 구매 취소
     */
    @Transactional
    public Purchase cancelPurchase(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalArgumentException("구매 내역을 찾을 수 없습니다."));

        // 재고 복구
        Product product = purchase.getProduct();
        product.increaseStock(purchase.getQuantity());
        productRepository.save(product);

        purchase.cancel();
        return purchaseRepository.save(purchase);
    }

    /**
     * 사용자 구매 내역 조회
     */
    public List<Purchase> getUserPurchases(Long userId) {
        return purchaseRepository.findByUserIdWithProductOrderByCreatedAtDesc(userId);
    }

    /**
     * 특정 구매 상세 조회
     */
    public Purchase getPurchaseById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalArgumentException("구매 내역을 찾을 수 없습니다."));
    }

    /**
     * 상태별 구매 내역 조회
     */
    public List<Purchase> getPurchasesByStatus(PurchaseStatus status) {
        return purchaseRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    /**
     * 환불 가능한 구매 내역 조회
     */
    public List<Purchase> getRefundablePurchases(Long userId) {
        return purchaseRepository.findRefundablePurchasesByUserId(userId);
    }
} 