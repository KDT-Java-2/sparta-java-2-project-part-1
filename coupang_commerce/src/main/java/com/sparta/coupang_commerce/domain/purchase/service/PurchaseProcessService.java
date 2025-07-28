package com.sparta.coupang_commerce.domain.purchase.service;

import com.sparta.coupang_commerce.common.enums.PurchaseStatusType;
import com.sparta.coupang_commerce.common.exception.ServiceException;
import com.sparta.coupang_commerce.common.exception.ServiceExceptionCode;
import com.sparta.coupang_commerce.domain.product.entity.Product;
import com.sparta.coupang_commerce.domain.product.repository.ProductRepository;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseProductRequest;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseResponse;
import com.sparta.coupang_commerce.domain.purchase.entity.Purchase;
import com.sparta.coupang_commerce.domain.purchase.entity.PurchaseProduct;
import com.sparta.coupang_commerce.domain.purchase.mapper.PurchaseMapper;
import com.sparta.coupang_commerce.domain.purchase.repository.PurchaseProductRepository;
import com.sparta.coupang_commerce.domain.purchase.repository.PurchaseRepository;
import com.sparta.coupang_commerce.domain.user.entity.User;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseProcessService {

  private final PurchaseMapper purchaseMapper;
  private final PurchaseRepository purchaseRepository;
  private final PurchaseProductRepository purchaseProductRepository;
  private final ProductRepository productRepository;

  @Transactional
  public PurchaseResponse process(User user, List<PurchaseProductRequest> requestList) {

    Purchase purchase = savePurchase(user);
    List<PurchaseProduct> purchaseProducts = createPurchaseProducts(requestList, purchase);
    BigDecimal totalPrice = calculateTotalPrice(purchaseProducts);

    purchase.setTotalPrice(totalPrice);
    purchaseProductRepository.saveAll(purchaseProducts);

    return purchaseMapper.toResponse(purchase);
  }

  private Purchase savePurchase(User user) {
    return purchaseRepository.save(Purchase.builder()
        .user(user)
        .status(PurchaseStatusType.COMPLETION)
        .build());
  }

  private List<PurchaseProduct> createPurchaseProducts(List<PurchaseProductRequest> itemRequest, Purchase purchase) {

    List<PurchaseProduct> purchaseProducts = new ArrayList<>();

    for (PurchaseProductRequest productRequest : itemRequest) {

      Product product = productRepository.findById(productRequest.getProductId()).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

      validatePurchase(product, productRequest.getQuantity());

      product.decreaseStock(productRequest.getQuantity());

      PurchaseProduct purchaseProduct = PurchaseProduct.builder()
          .product(product)
          .purchase(purchase)
          .quantity(productRequest.getQuantity())
          .price(product.getPrice())
          .build();

      purchaseProducts.add(purchaseProduct);
    }
    return purchaseProducts;
  }

  private void validatePurchase(Product product, int requestedQuantity) {
    if (product.getStock() < requestedQuantity) {
      throw new ServiceException(ServiceExceptionCode.INSUFFICIENT_STOCK);
    }
  }

  private BigDecimal calculateTotalPrice(List<PurchaseProduct> purchaseProducts) {
    return purchaseProducts.stream()
        .map(purchaseProduct -> purchaseProduct.getPrice()
            .multiply(BigDecimal.valueOf(purchaseProduct.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  //  @Transactional 리팩토링 전
//  public PurchaseResponse placePurchase(PurchaseRequest request) {
//    User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));
//
//    Purchase purchase = purchaseRepository.save(Purchase.builder().user(user).status(PurchaseStatusType.COMPLETION).build());
//
//    BigDecimal totalPrice = BigDecimal.ZERO;
//    List<PurchaseProduct> purchaseProducts = new ArrayList<>();
//
//    for (PurchaseProductRequest productRequest : request.getPurchaseProducts()) {
//
//      Product product = productRepository.findById(productRequest.getProductId()).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
//
//      if (product.getStock() < productRequest.getQuantity()) {
//        throw new ServiceException(ServiceExceptionCode.INSUFFICIENT_STOCK);
//      }
//
//      product.decreaseStock(productRequest.getQuantity());
//
//      PurchaseProduct purchaseProduct = PurchaseProduct.builder()
//          .product(product)
//          .purchase(purchase)
//          .quantity(productRequest.getQuantity())
//          .price(product.getPrice())
//          .build();
//
//      purchaseProducts.add(purchaseProduct);
//      totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(productRequest.getQuantity())));
//    }
//
//    purchase.setTotalPrice(totalPrice);
//    purchaseProductRepository.saveAll(purchaseProducts);
//
//    return purchaseMapper.toResponse(purchase);
//  }

}
