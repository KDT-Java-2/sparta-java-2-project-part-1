package com.sparta.commerce_project_01.domain.purchase.service;

import com.sparta.commerce_project_01.common.constant.Constants;
import com.sparta.commerce_project_01.common.enums.PurchaseStatus;
import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import com.sparta.commerce_project_01.common.enums.exception.ServiceExceptionCode;
import com.sparta.commerce_project_01.domain.product.entity.Product;
import com.sparta.commerce_project_01.domain.product.repository.ProductRepository;
import com.sparta.commerce_project_01.domain.purchase.dto.PurchaseCancelResponse;
import com.sparta.commerce_project_01.domain.purchase.dto.PurchaseProductResponse;
import com.sparta.commerce_project_01.domain.purchase.entity.Purchase;
import com.sparta.commerce_project_01.domain.purchase.entity.PurchaseProduct;
import com.sparta.commerce_project_01.domain.purchase.repository.PurchaseProductRepository;
import com.sparta.commerce_project_01.domain.purchase.repository.PurchaseRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseCancelService {

  private final PurchaseRepository purchaseRepository;
  private final PurchaseProductRepository purchaseProductRepository;
  private final ProductRepository productRepository;

  @Transactional
  public PurchaseCancelResponse cancelPurchase(Long purchaseId, Long userId) {
    // 1. 구매 정보 조회 및 권한 확인
    Purchase purchase = purchaseRepository.findByIdAndUser_Id(purchaseId, userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.PURCHASE_NOT_FOUND));

    // 2. 취소 가능 여부 확인 및 상태 변경
    validatePurchaseStatus(purchase);
    purchase.setStatus(PurchaseStatus.CANCELLED);

    // 3. 구매 상품 목록 조회
    List<PurchaseProduct> purchaseProducts = purchaseProductRepository.findByPurchase_Id(
        purchase.getId());

    // 4. 재고 복원
    restoreProductStock(purchaseProducts);

    List<PurchaseProductResponse> purchaseProductResponses = getPurchaseProductResponses(
        purchaseProducts);

    // 5. TODO : 결재취소 API, 주문 취소알람 등

    return PurchaseCancelResponse.builder()
        .purchaseId(purchase.getId())
        .purchaseStatus(purchase.getStatus())
        .cancelledAt(purchase.getUpdatedAt())
        .cancelledProducts(purchaseProductResponses)
        .message(Constants.PURCHASE_CANCEL_MESSAGE)
        .build();
  }

  private void restoreProductStock(List<PurchaseProduct> purchaseProducts) {
    for (PurchaseProduct purchaseProduct : purchaseProducts) {
      Product product = purchaseProduct.getProduct();

      // 재고 복원 (현재 재고 + 취소된 수량)
      product.increaseStock(purchaseProduct.getQuantity());

      productRepository.save(product);
    }
  }

  private void validatePurchaseStatus(Purchase purchase) {
    if (purchase.getStatus() != PurchaseStatus.PENDING) {
      throw new ServiceException(ServiceExceptionCode.PURCHASE_CANNOT_CANCEL);
    }
    purchase.setStatus(PurchaseStatus.CANCELLED); // 취소 불가능한 상태인 경우 예외 재던지기
  }

  private List<PurchaseProductResponse> getPurchaseProductResponses(
      List<PurchaseProduct> purchaseProducts) {
    return purchaseProducts.stream()
        .map((purchaseProduct) -> {
          Product product = purchaseProduct.getProduct();
          BigDecimal totalPrice = purchaseProduct.getPrice()
              .multiply(BigDecimal.valueOf(purchaseProduct.getQuantity()));

          return PurchaseProductResponse.builder()
              .productId(product.getId())
              .productName(product.getName())
              .quantity(purchaseProduct.getQuantity())
              .price(purchaseProduct.getPrice())
              .totalPrice(totalPrice)
              .build();
        }).toList();
  }
}