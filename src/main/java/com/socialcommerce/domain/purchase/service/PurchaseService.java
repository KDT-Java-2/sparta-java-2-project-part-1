package com.socialcommerce.domain.purchase.service;

import com.socialcommerce.common.enums.PurchaseStatus;
import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.domain.cart.entity.Cart;
import com.socialcommerce.domain.cart.entity.CartItem;
import com.socialcommerce.domain.cart.repository.CartRepository;
import com.socialcommerce.domain.coupon.entity.Coupon;
import com.socialcommerce.domain.coupon.repository.CouponRepository;
import com.socialcommerce.domain.product.entity.Product;
import com.socialcommerce.domain.product.repository.ProductRepository;
import com.socialcommerce.domain.purchase.dto.PurchaseCreateRequest;
import com.socialcommerce.domain.purchase.dto.PurchaseResponse;
import com.socialcommerce.domain.purchase.entity.Purchase;
import com.socialcommerce.domain.purchase.entity.PurchaseProduct;
import com.socialcommerce.domain.purchase.repository.PurchaseProductRepository;
import com.socialcommerce.domain.purchase.repository.PurchaseRepository;
import com.socialcommerce.domain.user.entity.User;
import com.socialcommerce.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final PurchaseProductRepository purchaseProductRepository;
  private final ProductRepository productRepository;
  private final CouponRepository couponRepository;
  private final CartRepository cartRepository;
  private final UserRepository userRepository;

  @Transactional
  public PurchaseResponse createPurchaseFromCart(Long userId, PurchaseCreateRequest request){
    log.info("userId: {} / request: {}, {}", userId, request.getShippingAddress(), request.getCouponId());

    // 현재 user의 장바구니에
    // 제품의 재고확인 -> 재고감소 -> 구매 및 구매항목생성 -> 장바구니 비우기
    User user = userRepository.findById(userId)
        .orElseThrow(()->new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));
    // 장바구니 존재하는지 체크
    Cart cart = cartRepository.findByUser(user)
        .orElseThrow(()->new ServiceException(ServiceExceptionCode.NOT_FOUND_CART));
    List<CartItem> cartItems = cart.getCartItems();
    if (cartItems.isEmpty()) throw new ServiceException(ServiceExceptionCode.IS_EMPTY_CART_ITEMS);

    // 쿠폰
    Coupon coupon = null;
    if (request.getCouponId() != null) {
      coupon = couponRepository.findById(request.getCouponId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_COUPON));
      // (쿠폰 검증/사용처리 등)
    }

    // 3. 재고 체크 및 차감
    for (CartItem item : cartItems) {
      Product product = item.getProduct();
      if (product.getStock() < item.getQuantity()) {
        throw new ServiceException(ServiceExceptionCode.INSUFFICIENT_STOCK);
      }
      product.decreaseStock(item.getQuantity());
      productRepository.save(product);
    }

    // 4. Purchase 생성
//    BigDecimal totalPrice = calcTotalPrice(cartItems, coupon);
//    Purchase purchase = Purchase.builder()
//        .user(user)
//        .totalPrice(totalPrice)
//        .status(PurchaseStatus.PENDING)
//        .shippingAddress(request.getShippingAddress())
//        .coupon(coupon)
//        .build();
//    purchaseRepository.save(purchase);
//
//    // 5. PurchaseProduct 생성
//    for (CartItem item : cartItems) {
//      PurchaseProduct purchaseProduct = PurchaseProduct.builder()
//          .purchase(purchase)
//          .product(item.getProduct())
//          .quantity(item.getQuantity())
//          .price(item.getProduct().getPrice())
//          .build();
//      purchaseProductRepository.save(purchaseProduct);
//    }
//
//    // 6. 장바구니 비우기
//    cart.clearItems();
//    cartRepository.save(cart);

    // 7. (필요 시) 쿠폰 사용 처리
    // coupon.use(); couponRepository.save(coupon);

    // 8. 결과 반환
    return null;

  }


//  public BigDecimal calcTotalPrice(List<CartItem> cartItems, Coupon coupon) {
//    // 1. 모든 상품의 (수량 × 가격)을 합산
//    BigDecimal total = BigDecimal.ZERO;
//    for (CartItem item : cartItems) {
//      BigDecimal price = item.getProduct().getPrice(); // 단가
//      BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
//      total = total.add(price.multiply(quantity));
//    }
//
//    // 2. 쿠폰(할인)이 있으면 할인 적용
//    if (coupon != null && coupon.isValid()) {
//      if (coupon.getType() == CouponType.FIXED) {
//        // 정액 할인
//        total = total.subtract(coupon.getAmount());
//      } else if (coupon.getType() == CouponType.PERCENT) {
//        // 정률 할인 (예: 10% 할인)
//        BigDecimal discount = total.multiply(
//            BigDecimal.valueOf(coupon.getAmount()).divide(BigDecimal.valueOf(100))
//        );
//        total = total.subtract(discount);
//      }
//      // 음수 방지
//      if (total.compareTo(BigDecimal.ZERO) < 0) {
//        total = BigDecimal.ZERO;
//      }
//    }
//    return total;
//  }
}
