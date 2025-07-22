package com.socialcommerce.domain.product.service;

import com.socialcommerce.common.enums.PurchaseStatus;
import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.domain.category.entity.Category;
import com.socialcommerce.domain.category.repository.CategoryRepository;
import com.socialcommerce.domain.product.dto.ProductCreateRequest;
import com.socialcommerce.domain.product.dto.ProductIdResponse;
import com.socialcommerce.domain.product.dto.ProductResponse;
import com.socialcommerce.domain.product.entity.Product;
import com.socialcommerce.domain.product.repository.ProductRepository;
import com.socialcommerce.domain.purchase.repository.PurchaseProductRepository;
import com.socialcommerce.domain.purchase.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final PurchaseProductRepository purchaseProductRepository;

  // 새로운! 상품등록
  @Transactional
  public ProductIdResponse createProduct(ProductCreateRequest request){

    // 1. 시스템 내에서 중복되는 상품명이 있는지 체크
    if(productRepository.findByName(request.getName()).isPresent()){
      throw new ServiceException(ServiceExceptionCode.DUPLICATED_PRODUCT);
    }
    // 2. categoryId로 카테고리 존재여부 체크
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_EXIST_CATEGORY));

    Product product = Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .stock(request.getStock())
        .category(category)
        .build();

    Product saved = productRepository.save(product);

    return ProductIdResponse.builder()
        .id(saved.getId())
        .build();
  }
  
  // 기존 상품 수정
  @Transactional
  public ProductResponse updateProduct(Long productId, ProductCreateRequest request){

    // 1. 해당 productId 존재여부 체크
    Product product = productRepository.findById(productId)
        .orElseThrow(()-> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    // 2. RequestBody 안에 categoryId 의 존재여부 체크
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_EXIST_CATEGORY));

    // 3. 수정할 가격/재고 음수값 검증
    if(request.getPrice().compareTo(BigDecimal.ZERO) < 0){
      throw new ServiceException(ServiceExceptionCode.INVALID_PRICE);
    }

    // 4. 수정적용
    product.updateProduct(
        request.getName(),
        request.getDescription(),
        request.getPrice(),
        request.getStock(),
        category
    );

    // save() 없이 트랜잭션 끝나면 자동 update

    // 5. 응답
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .categoryId(category.getId())
        .build();
  }

  // 상품삭제
  @Transactional
  public void deleteProduct(Long productId){
    // Purchase와 PurchaseProduct 테이블을 조인하여,
    // 해당 productId가 status가 'COMPLETED'인 Purchase에 있는지 검증하는 로직
    if (purchaseProductRepository.existsByProductIdAndPurchaseStatus(productId, PurchaseStatus.COMPLETED)) {
      throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_PRODUCT_ALREADY_PURCHASED);
    }

    productRepository.deleteById(productId);

  }
}
