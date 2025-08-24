package com.sparta.java_02.domain.purchase.service;

import com.sparta.java_02.common.enums.PurchaseStatus;
import com.sparta.java_02.common.exception.ServiceException;
import com.sparta.java_02.common.exception.ServiceExceptionCode;
import com.sparta.java_02.domain.product.entity.Product;
import com.sparta.java_02.domain.product.repository.ProductRepository;
import com.sparta.java_02.domain.purchase.dto.PurchaseRequest;
import com.sparta.java_02.domain.purchase.dto.PurchaseResponse;
import com.sparta.java_02.domain.purchase.entity.Purchase;
import com.sparta.java_02.domain.purchase.entity.PurchaseProduct;
import com.sparta.java_02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java_02.domain.user.entity.User;
import com.sparta.java_02.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  @Transactional
  public PurchaseResponse placePurchase(PurchaseRequest request) {
    // 1. 사용자 조회
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    // 2. 상품 조회
    Product product = productRepository.findById(request.getProductId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    // 3. 재고 확인 및 감소
    if (product.getStock() < request.getQuantity()) {
      throw new ServiceException(ServiceExceptionCode.INSUFFICIENT_STOCK);
    }
    // Product 엔티티에 decreaseStock 메서드가 있다고 가정
    // public void decreaseStock(Integer quantity) { this.stock -= quantity; }
    product.decreaseStock(request.getQuantity());

    // 4. 구매(Purchase) 및 구매 항목(PurchaseProduct) 생성 및 저장
    Purchase purchase = Purchase.builder()
        .user(user)
        .totalPrice(product.getPrice().multiply(new BigDecimal(request.getQuantity())))
        .status(PurchaseStatus.COMPLETION)
        .build();

    // `PurchaseProduct` 엔티티는 '배송 주소' 필드가 없어 예제 코드와 약간 다르게 구현
    PurchaseProduct purchaseProduct = PurchaseProduct.builder()
        .purchase(purchase)
        .product(product)
        .quantity(request.getQuantity())
        .price(product.getPrice()) // 주문 시점의 가격 기록
        .build();

    // 연관관계 편의 메서드가 없으므로 수동으로 추가
    purchase.getPurchaseProducts().add(purchaseProduct);

    Purchase savedPurchase = purchaseRepository.save(purchase);

    // 5. 응답 DTO로 변환하여 반환
    return PurchaseResponse.fromEntity(savedPurchase);
  }
}