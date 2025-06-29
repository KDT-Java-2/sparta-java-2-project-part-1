package com.java_project.part_1.domain.admin.service;

import com.java_project.part_1.common.enums.ProductStatus;
import com.java_project.part_1.common.exception.ServiceException;
import com.java_project.part_1.common.exception.ServiceExceptionCode;
import com.java_project.part_1.domain.category.dto.CategoryRequest;
import com.java_project.part_1.domain.category.entity.Category;
import com.java_project.part_1.domain.category.repository.CategoryRepository;
import com.java_project.part_1.domain.product.dto.ProductCreateRequest;
import com.java_project.part_1.domain.product.entity.Product;
import com.java_project.part_1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  // 카테고리 등록
  public void createCategory(CategoryRequest request) {
    Category parent = null;

    if (!ObjectUtils.isEmpty(request.getParentId())) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }

    Category category = Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .parent(parent)
        .build();
    categoryRepository.save(category);
  }

  // 카테고리 수정
  public void updateCategory(Long categoryId, CategoryRequest request) {
    Category parent = null;

    if (!ObjectUtils.isEmpty(request.getParentId())) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }

    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setParent(parent);

    categoryRepository.save(category);
  }

  // 카테고리 삭제
  public void deleteCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    boolean hasChildren = categoryRepository.existsByParent_Id(categoryId);
    if (hasChildren) {
      throw new ServiceException(ServiceExceptionCode.EXIST_CHILDREN_CATEGORY);
    }

    boolean hasProducts = productRepository.existsByCategory_Id(categoryId);
    if (hasProducts) {
      throw new ServiceException(ServiceExceptionCode.EXIST_CATEGORY_PRODUCT);
    }

    categoryRepository.delete(category);
  }

  // 상품 등록
  public Product createProduct(ProductCreateRequest request) {
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    if (productRepository.existsByName(request.getName())) {
      throw new ServiceException(ServiceExceptionCode.PRODUCT_ALREADY_EXIST);
    }

    Product product = Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .stock(request.getStock())
        .category(category)
        .status(ProductStatus.SELLING)
        .build();
    productRepository.save(product);

    return product;
  }

  // 상품 수정
  public Product updateProduct(Long productId, ProductCreateRequest request) {
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());
    product.setCategory(category);
    product.setStatus(request.getStatus());

    productRepository.save(product);

    return product;
  }

  // 상품 삭제
  public void deleteProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    /**
     * - **(핵심 로직)** 삭제하려는 상품이 '주문 완료' 상태의 주문(`Order`)에 포함되어 있는지 확인해야 합니다.
     * - `Order`와 `OrderItem` 테이블을 조인하여, 해당 `productId`가 `status`가 'COMPLETED'인 `Order`에 있는지 검증하는 로직이 필요합니다.
     * - 만약 포함되어 있다면 삭제를 거부하고 에러를 반환해야 합니다.
     */

    productRepository.delete(product);
  }
}
