package com.socialcommerce.domain.product.service;

import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.domain.category.entity.Category;
import com.socialcommerce.domain.category.repository.CategoryRepository;
import com.socialcommerce.domain.product.dto.ProductCreateRequest;
import com.socialcommerce.domain.product.dto.ProductIdResponse;
import com.socialcommerce.domain.product.dto.ProductResponse;
import com.socialcommerce.domain.product.entity.Product;
import com.socialcommerce.domain.product.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  // 새로운! 상품등록
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
  public ProductResponse updateProduct(Long productId, ProductCreateRequest request){

    // 1. 해당 productId 존재여부 체크
    Optional<Product> product = productRepository.findById(productId);

    if(product.isEmpty()){
      throw new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT);
    } else {
      // 2. RequestBody 안에 categoryId 의 존재여부 체크
      Category category = categoryRepository.findById(request.getCategoryId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_EXIST_CATEGORY));

      // 3. 수정할 가격/재고 음수값 검증
      if(request.getPrice().compareTo(BigDecimal.ZERO) < 0){
        throw new ServiceException(ServiceExceptionCode.INVALID_PRICE);
      }
    }

    // 4. 수정

    return ProductResponse.builder().build();
  }

}
