package com.socialcommerce.domain.cart.service;

import com.socialcommerce.domain.cart.dto.CartItemRequest;
import com.socialcommerce.domain.cart.dto.CartResponse;
import com.socialcommerce.domain.cart.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;

  @Transactional
  public void addCartItem(Long userId, CartItemRequest request){
    // 추가로직
  }

  @Transactional
  public CartResponse getCart(Long userId){
    // 조회로직
    return null;
  }

  @Transactional
  public void deleteCartItem(Long cartItemId){

  }
}
