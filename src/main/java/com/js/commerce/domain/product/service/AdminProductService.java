package com.js.commerce.domain.product.service;

import com.js.commerce.common.exception.ServiceException;
import com.js.commerce.common.exception.ServiceExceptionCode;
import com.js.commerce.domain.category.entity.Category;
import com.js.commerce.domain.category.repository.CategoryRepository;
import com.js.commerce.domain.product.dto.admin.AdminProductCreateRequest;
import com.js.commerce.domain.product.dto.admin.AdminProductCreateResponse;
import com.js.commerce.domain.product.entity.Product;
import com.js.commerce.domain.product.repository.ProductRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Transactional
  public AdminProductCreateResponse createProduct(AdminProductCreateRequest request) {
    // 1) 중복 상품명 검사
    if (productRepository.existsByName(request.getName())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
    }

    // 2) 가격, 재고 음수 검사 (로직에서도 더블 체크)
    if (request.getPrice() < 0 || request.getStock() < 0) {
      throw new ServiceException(ServiceExceptionCode.INVALID_PRICE_STOCK);
    }

    // 3) 카테고리 존재 여부 검증
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));

    // 4) 엔티티 생성 및 저장
    Product product = Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(BigDecimal.valueOf(request.getPrice()))
        .stock(request.getStock())
        .category(category)
        .build();
    Product saved = productRepository.save(product);

    // 5) 응답 반환
    return new AdminProductCreateResponse(saved.getId());

  }

}
