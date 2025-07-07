package com.sparta.commerce.domain.product.dto.product;

import com.sparta.commerce.domain.product.dto.image.ProductImageRequest;
import com.sparta.commerce.domain.product.dto.option.ProductOptionRequest;
import com.sparta.commerce.domain.product.dto.product.productItem.ProductItemRequest;
import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
  // 상품 기본 정보
  String name;
  String brand;
  String description;
  Long categoryId;
  BigDecimal basePrice;

  // 상품 이미지 정보
  List<ProductImageRequest> images;

  // 옵션 그룹 + 옵션 항목 (추가 가격 포함)
  List<ProductOptionRequest> optionGroups;

  // 옵션 조합별 재고 (product_item 역할)
  List<ProductItemRequest> productItems;
}
