package me.chahyunho.projectweek1.domain.admin.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.common.enums.exception.ServiceException;
import me.chahyunho.projectweek1.common.enums.exception.ServiceExceptionCode;
import me.chahyunho.projectweek1.domain.admin.dto.AdminCategoryRequest;
import me.chahyunho.projectweek1.domain.admin.dto.AdminCategoryResponse;
import me.chahyunho.projectweek1.domain.admin.dto.AdminCategoryUpdateResponse;
import me.chahyunho.projectweek1.domain.admin.dto.AdminProductRequest;
import me.chahyunho.projectweek1.domain.admin.dto.AdminProductResponse;
import me.chahyunho.projectweek1.domain.admin.dto.AdminProductUpdateResponse;
import me.chahyunho.projectweek1.domain.category.entity.Category;
import me.chahyunho.projectweek1.domain.category.repository.CategoryRepository;
import me.chahyunho.projectweek1.domain.product.dto.CategoryProductDTO;
import me.chahyunho.projectweek1.domain.product.entity.Product;
import me.chahyunho.projectweek1.domain.product.repository.CategoryProductQueryRepository;
import me.chahyunho.projectweek1.domain.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final CategoryProductQueryRepository categoryProductQueryRepository;

  @Transactional
  public AdminProductResponse createAdminProduct(AdminProductRequest request) {

    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(
            ServiceExceptionCode.NOT_FOUNT_CATEGORY));

    if (productRepository.existsByName(request.getName())) {
      throw new ServiceException(ServiceExceptionCode.ALREADY_EXISTS_DATA);
    }

    Product product = productRepository.save(Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .stock(request.getStock())
        .category(category)
        .build());

    return AdminProductResponse.builder().productId(product.getId()).build();
  }

  @Transactional
  public AdminProductUpdateResponse updateAdminProduct(Long productId,
      AdminProductRequest request) {

    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(
            ServiceExceptionCode.NOT_FOUNT_CATEGORY));

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUNT_DATA));

    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());
    product.setCategory(category);

    return AdminProductUpdateResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .categoryId(product.getCategory().getId())
        .build();
  }

  @Transactional
  public void deleteAdminProduct(Long productId) {
    productRepository.deleteById(productId);
  }

  // 카테고리 등록
  public AdminCategoryResponse createAdminCategory(AdminCategoryRequest request) {
    Category parent = null;
    if (request.getParentId() != null && 0 < request.getParentId().intValue()) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUNT_CATEGORY_PARENT));
    }

    Category category = Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .parent(parent)
        .build();

    Category sevedCategory = categoryRepository.save(category);

    return AdminCategoryResponse.builder().id(sevedCategory.getId()).build();
  }

  // 카테고리 수정
  @Transactional
  public AdminCategoryUpdateResponse updateAdminCategory(Long categoryId,
      AdminCategoryRequest request) {

    Category parent = null;

    // 부모 카테고리 검증
    if (categoryId.longValue() == request.getParentId().longValue()) {
      throw new ServiceException(ServiceExceptionCode.SELF_PARENT_NOT_ALLOWED);
    }

    if (0 < request.getParentId().intValue()) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUNT_CATEGORY_PARENT));
    }

    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUNT_CATEGORY));

    category.setName(request.getName());
    category.setParent(parent);
    category.setDescription(request.getDescription());

    return AdminCategoryUpdateResponse.builder()
        .name(category.getName())
        .description(category.getDescription())
        .parentId(category.getParent() != null ? category.getParent().getId() : null)
        .build();
  }

  // 카테 고리 삭제
  @DeleteMapping("/categories/{categoryId}")
  public void deleteAdminCategory(@PathVariable Long categoryId) {

    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUNT_CATEGORY));

    // 하위 카테고리 검증
    if (!category.getChildren().isEmpty()) {
      throw new ServiceException(ServiceExceptionCode.SUBCATEGORY_EXISTS);
    }

    List<CategoryProductDTO> findCategoryProducts = categoryProductQueryRepository.findCategoryProductsByCategoryId(
        category.getId());

    // 카테고리 속한 상품 검증
    if (!findCategoryProducts.isEmpty()) {
      throw new ServiceException(ServiceExceptionCode.HAS_ASSOCIATED_PRODUCTS);
    }

    categoryRepository.deleteById(categoryId);
  }

}
