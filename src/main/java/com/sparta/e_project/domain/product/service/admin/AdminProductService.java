package com.sparta.e_project.domain.product.service.admin;

import com.sparta.e_project.common.exception.ServiceException;
import com.sparta.e_project.common.exception.ServiceExceptionCode;
import com.sparta.e_project.domain.category.dto.CategoryProductResponse;
import com.sparta.e_project.domain.category.entity.Category;
import com.sparta.e_project.domain.category.repository.admin.AdminCategoryRepository;
import com.sparta.e_project.domain.product.dto.admin.AdminProductRequest;
import com.sparta.e_project.domain.product.dto.admin.AdminProductResponse;
import com.sparta.e_project.domain.product.dto.admin.AdminProductUpdateResponse;
import com.sparta.e_project.domain.product.entity.Product;
import com.sparta.e_project.domain.product.repository.admin.AdminProductRepository;
import com.sparta.e_project.domain.purchase.repository.AdminPurchaseProductRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProductService {

  private final AdminProductRepository adminProductRepository;

  private final AdminCategoryRepository adminCateogryRepository;

  private final AdminPurchaseProductRepository adminPurchaseProductRepository;

  @Transactional
  public AdminProductResponse createProducts(AdminProductRequest adminProductRequest) {

    Category category = adminCateogryRepository.findById(adminProductRequest.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    if (adminProductRequest.getPrice() < 0 || adminProductRequest.getStock() < 0) {
      throw new ServiceException(ServiceExceptionCode.NEGATIVE_NUMBER);
    }

    if (adminProductRepository.existsByName(adminProductRequest.getName())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
    }

    Product save = adminProductRepository.save(Product.builder()
        .name(adminProductRequest.getName())
        .description(adminProductRequest.getDescription())
        .price(BigDecimal.valueOf(adminProductRequest.getPrice()))
        .stock(adminProductRequest.getStock())
        .category(category)
        .build());

    return AdminProductResponse.builder().productId(save.getId()).build();
  }

  @Transactional
  public AdminProductUpdateResponse updateProduct(Long productId,
      AdminProductRequest updateRequest) {

    Product product = adminProductRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    Category category = adminCateogryRepository.findById(updateRequest.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    if (updateRequest.getPrice() < 0 || updateRequest.getStock() < 0) {
      throw new ServiceException(ServiceExceptionCode.NEGATIVE_NUMBER);
    }

    if (adminProductRepository.existsByName(updateRequest.getName())) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
    }

    product.putProduct(updateRequest.getName(), updateRequest.getDescription()
        , BigDecimal.valueOf(updateRequest.getPrice()), updateRequest.getStock(), category);

    return AdminProductUpdateResponse.builder()
        .productId(product.getId())
        .description(product.getDescription())
        .price(product.getPrice())
        .name(product.getName())
        .stock(product.getStock())
        .category(CategoryProductResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .build())
        .build();
  }

  @Transactional
  public void deleteProduct(Long productId) {
    Product product = adminProductRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    // 주문 테이블과 주문상품테이블
    if (adminPurchaseProductRepository.existsByProductId(productId)) {
      throw new ServiceException(ServiceExceptionCode.EXIST_COMPLETED_PRODUCT);
    }

    adminProductRepository.delete(product);
  }
}
