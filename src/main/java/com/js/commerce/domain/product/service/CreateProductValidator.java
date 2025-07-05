package com.js.commerce.domain.product.service;

import com.js.commerce.common.exception.ServiceException;
import com.js.commerce.common.exception.ServiceExceptionCode;
import com.js.commerce.domain.product.dto.admin.AdminProductCreateRequest;
import com.js.commerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductValidator {

  private final ProductRepository productRepository;

  public void validateRequest(AdminProductCreateRequest request) {
    // 중복 상품명 검사
    if (productRepository.existsByName(request.getName())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
    }

    // 가격, 재고 음수 검사 (로직에서도 더블 체크)
    if (request.getPrice() < 0 || request.getStock() < 0) {
      throw new ServiceException(ServiceExceptionCode.INVALID_PRICE_STOCK);
    }
  }


}
