package com.socialcommerce.domain.cart.service;

import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.domain.cart.dto.CartItemRequest;
import com.socialcommerce.domain.cart.dto.CartItemResponse;
import com.socialcommerce.domain.cart.dto.CartResponse;
import com.socialcommerce.domain.cart.entity.Cart;
import com.socialcommerce.domain.cart.entity.CartItem;
import com.socialcommerce.domain.cart.repository.CartItemRepository;
import com.socialcommerce.domain.cart.repository.CartRepository;
import com.socialcommerce.domain.product.entity.Product;
import com.socialcommerce.domain.product.repository.ProductRepository;
import com.socialcommerce.domain.user.entity.User;
import com.socialcommerce.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  @Transactional
  public void addCartItem(Long userId, CartItemRequest request) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    Cart cart = cartRepository.findByUser(user)
        .orElseGet(() -> cartRepository.save(Cart.builder().user(user).build()));

    Product product = productRepository.findById(request.getProductId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    // 기존 상품 존재 여부 확인
    Optional<CartItem> existingItem = cart.getCartItems().stream()
        .filter(item -> item.getProduct().equals(product))
        .findFirst();

    if (existingItem.isPresent()) {
      existingItem.get().addQuantity(request.getQuantity());
    } else {
      CartItem newItem = CartItem.builder()
          .product(product)
          .quantity(request.getQuantity())
          .build();

      cart.addCartItem(newItem); // 연관관계 설정 포함
    }

    cartRepository.save(cart); // cascade로 cartItem도 저장됨
  }

  @Transactional
  public CartResponse getCart(Long userId){

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    Cart cart = cartRepository.findByUser(user)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CART));

    List<CartItemResponse> cartItemResponses = cart.getCartItems().stream()
        .map(item -> CartItemResponse.builder()
            .productId(item.getId())
            .productName(item.getProduct().getName())
            .quantity(item.getQuantity())
            .price(item.getProduct().getPrice())
            .build()
        ).toList();

    BigDecimal totalPrice = cartItemResponses.stream()
        .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return CartResponse.builder()
        .cartId(cart.getId())
        .cartItems(cartItemResponses)
        .totalPrice(totalPrice)
        .build();
  }

  @Transactional
  public void deleteCartItem(Long userId, Long productId){
    // cart_id 가 로그인한 유저의 id 의 product_id 이면 지우기
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    Product product = productRepository.findById(productId)
        .orElseThrow(()->new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    cartItemRepository.deleteByCartIdAndProductId(user.getId(), product.getId());
  }
}
