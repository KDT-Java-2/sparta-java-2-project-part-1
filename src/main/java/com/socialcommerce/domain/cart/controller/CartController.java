package com.socialcommerce.domain.cart.controller;

import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.auth.dto.CustomUserDetails;
import com.socialcommerce.domain.cart.dto.CartItemRequest;

import com.socialcommerce.domain.cart.dto.CartResponse;
import com.socialcommerce.domain.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

  private final CartService cartService;

  /*
  * {
  "productId": 1,
  "quantity": 2
  }*/
  @PostMapping("/items")
  public ApiResponse<Void> addCartItem(
      @RequestBody @Valid CartItemRequest request,
      @AuthenticationPrincipal CustomUserDetails user){

    log.info("장바구니에 상품 추가 요청: userId={}, productId={}, quantity={}",
        user.getId(), request.getProductId(), request.getQuantity());

    cartService.addCartItem(user.getId(), request);
    return ApiResponse.success();
  }

  @GetMapping
  public ApiResponse<CartResponse> getCart(@AuthenticationPrincipal CustomUserDetails user){
    CartResponse response = cartService.getCart(user.getId());
    return ApiResponse.success(response);
  }

  @DeleteMapping("/items/{productId}")
  public ApiResponse<Void> deleteCartItem(@PathVariable Long productId
  , @AuthenticationPrincipal CustomUserDetails user){
    cartService.deleteCartItem(user.getId(), productId);
    return ApiResponse.success();
  }
}
