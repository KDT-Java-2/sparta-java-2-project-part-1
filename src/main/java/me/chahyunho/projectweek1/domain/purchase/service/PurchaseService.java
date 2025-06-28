package me.chahyunho.projectweek1.domain.purchase.service;


import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.common.enums.PurchaseStatus;
import me.chahyunho.projectweek1.common.enums.exception.ServiceException;
import me.chahyunho.projectweek1.common.enums.exception.ServiceExceptionCode;
import me.chahyunho.projectweek1.domain.product.entity.Product;
import me.chahyunho.projectweek1.domain.product.repository.ProductRepository;
import me.chahyunho.projectweek1.domain.purchase.dto.PurchaseRequest;
import me.chahyunho.projectweek1.domain.purchase.entity.Purchase;
import me.chahyunho.projectweek1.domain.purchase.entity.PurchaseProduct;
import me.chahyunho.projectweek1.domain.purchase.repository.PurchaseRepository;
import me.chahyunho.projectweek1.domain.user.entity.User;
import me.chahyunho.projectweek1.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final UserRepository userRepository;
  private final PurchaseRepository purchaseRepository;
  private final ProductRepository productRepository;

  @Transactional
  public void placePurchase(PurchaseRequest request) {
    Integer quantity = request.getQuantity();

    // 사용자 조회
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUNT_USER));

    // 상품조회
    Product product = productRepository.findById(request.getProductId())
        .orElseThrow(() -> new ServiceException(
            ServiceExceptionCode.NOT_FOUNT_DATA));

    // 수량검증
    if (product.getStock() < quantity) {
      throw new ServiceException(ServiceExceptionCode.INSUFFICIENT_STOCK);
    }

    // 상품재고 감소
    product.decreaseStock(quantity);

    // 주문생성
    Purchase purchase = Purchase.builder()
        .user(user)
        .totalPrice(product.getPrice().multiply(new BigDecimal(quantity)))
        .status(PurchaseStatus.COMPLETION)
        .shippingAddress(request.getShippingAddress())
        .build();

    // 주문아이템
    PurchaseProduct purchaseItem = PurchaseProduct.builder()
        .purchase(purchase)
        .product(product)
        .quantity(quantity)
        .price(product.getPrice())
        .build();

    purchase.addPurchaseItem(purchaseItem);

    Purchase savedPurchase = purchaseRepository.save(purchase);


  }
}
