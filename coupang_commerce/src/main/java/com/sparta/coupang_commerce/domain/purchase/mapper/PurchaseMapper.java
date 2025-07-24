package com.sparta.coupang_commerce.domain.purchase.mapper;

import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseCancelResponse;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseProductResponse;
import com.sparta.coupang_commerce.domain.purchase.dto.PurchaseResponse;
import com.sparta.coupang_commerce.domain.purchase.entity.Purchase;
import com.sparta.coupang_commerce.domain.purchase.entity.PurchaseProduct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

  PurchaseResponse toResponse(Purchase purchase);

  @Mapping(target = "purchaseId", source = "purchase.id")
  @Mapping(target = "purchaseStatus", source = "purchase.status")
  @Mapping(target = "canceledAt", source = "cancelledAt")
  @Mapping(target = "message", source = "message")
  @Mapping(target = "cancelledProducts", source = "cancelledProducts")
  PurchaseCancelResponse toCancelResponse(Purchase purchase, LocalDateTime cancelledAt, String message, List<PurchaseProductResponse> cancelledProducts);

  @Mapping(target = "productId", source = "purchaseProduct.product.id")
  @Mapping(target = "productName", source = "purchaseProduct.product.name")
  @Mapping(target = "totalPrice", source = "totalPrice")
  PurchaseProductResponse toPurchaseProductResponse(PurchaseProduct purchaseProduct, BigDecimal totalPrice);
}
