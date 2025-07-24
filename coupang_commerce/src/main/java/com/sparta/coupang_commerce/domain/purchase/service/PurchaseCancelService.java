package com.sparta.coupang_commerce.domain.purchase.service;

import com.sparta.coupang_commerce.common.constants.Constants;
import com.sparta.coupang_commerce.common.enums.PurchaseStatusType;
import com.sparta.coupang_commerce.common.exception.ServiceException;
import com.sparta.coupang_commerce.common.exception.ServiceExceptionCode;
import com.sparta.coupang_commerce.domain.product.entity.Product;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseCancelResponse;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseProductResponse;
import com.sparta.coupang_commerce.domain.purchase.entity.Purchase;
import com.sparta.coupang_commerce.domain.purchase.entity.PurchaseProduct;
import com.sparta.coupang_commerce.domain.purchase.mapper.PurchaseMapper;
import com.sparta.coupang_commerce.domain.purchase.repository.PurchaseProductRepository;
import com.sparta.coupang_commerce.domain.purchase.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseCancelService {

  private final PurchaseMapper purchaseMapper;
  private final PurchaseRepository purchaseRepository;
  private final PurchaseProductRepository purchaseProductRepository;


  @Transactional
  public PurchaseCancelResponse cancel(Long purchaseId, Long userId) {
    Purchase purchase = purchaseRepository.findByIdAndUser_Id(purchaseId, userId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));

    //취소를 하기전에 취소가 가능한 상태인지 확인 필요
    ValidatePurchaseStatus(purchase);
    purchase.setStatus(PurchaseStatusType.CANCELLED);

    List<PurchaseProduct> purchaseProducts = purchaseProductRepository.findByPurchaseId(purchaseId);

    restoreProductStock(purchaseProducts);

    //결제 취소 API
    //주문 취소 알람

    List<PurchaseProductResponse> purchaseProductResponseList = getPurchaseProductResponses(purchaseProducts);

    return purchaseMapper.toCancelResponse(purchase, LocalDateTime.now(), Constants.PURCHASE_CANCEL_MESSAGE, purchaseProductResponseList);

//    return PurchaseCancelResponse.builder()
//        .purchaseId(purchase.getId())
//        .purchaseStatus(purchase.getStatus())
//        .canceledAt(LocalDateTime.now())
//        .message(Constants.PURCHASE_CANCEL_MESSAGE)
//        .cancelledProducts(purchaseProductResponseList)
//        .build();
  }

  private void ValidatePurchaseStatus(Purchase purchase) {
    if (purchase.getStatus() != PurchaseStatusType.PENDING) {
      throw new ServiceException(ServiceExceptionCode.CANNOT_CANCEL);
    }
  }

  private void restoreProductStock(List<PurchaseProduct> purchaseProducts) {
    for (PurchaseProduct purchaseProduct : purchaseProducts) {
      Product product = purchaseProduct.getProduct();

      product.increaseStock(purchaseProduct.getQuantity());
    }
  }

  private List<PurchaseProductResponse> getPurchaseProductResponses(List<PurchaseProduct> purchaseProducts) {
    return purchaseProducts.stream()
        .map(purchaseProduct -> {
          Product product = purchaseProduct.getProduct();

          BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(purchaseProduct.getQuantity()));

          return purchaseMapper.toPurchaseProductResponse(purchaseProduct, totalPrice);

//          return PurchaseProductResponse.builder()
//              .productId(product.getId())
//              .productName(product.getName())
//              .quantity(purchaseProduct.getQuantity())
//              .price(purchaseProduct.getPrice())
//              .totalPrice(totalPrice)
//              .build();
        }).toList();
  }
}
