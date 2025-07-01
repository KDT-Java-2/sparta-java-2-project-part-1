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

@Service
@RequiredArgsConstructor
public class AdminService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final CategoryProductQueryRepository categoryProductQueryRepository;

  @Transactional
  public AdminProductResponse createAdminProduct(AdminProductRequest request) {

    // 카테고리 조회
    Category category = getCategory(request.getCategoryId());

    // 상품 이름 중복 검증
    validateAlreadyExistsData(request.getName());

    // 상품 등록
    return getAdminProductResponse(request, category);
  }

  // 카테고리 조회
  private Category getCategory(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(
            ServiceExceptionCode.NOT_FOUNT_CATEGORY));
  }

  // 상품 등록
  private AdminProductResponse getAdminProductResponse(AdminProductRequest request,
      Category category) {
    Product product = productRepository.save(Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .stock(request.getStock())
        .category(category)
        .build());

    return AdminProductResponse.builder().productId(product.getId()).build();
  }

  // 상품 이름 중복 검증
  private void validateAlreadyExistsData(String name) {
    if (productRepository.existsByName(name)) {
      throw new ServiceException(ServiceExceptionCode.ALREADY_EXISTS_DATA);
    }
  }

  @Transactional
  public AdminProductUpdateResponse updateAdminProduct(Long productId,
      AdminProductRequest request) {

    // 카테고리 조회
    Category category = getCategory(request.getCategoryId());

    // 상품 수정
    return getAdminProductUpdateResponse(productId, request, category);
  }

  // 상품 수정
  private AdminProductUpdateResponse getAdminProductUpdateResponse(Long productId,
      AdminProductRequest request, Category category) {
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

    // 부모 카테고리 조회
    Category parent = getParentCategory(request);

    // 카테고리 등록
    return getAdminCategoryResponse(request, parent);
  }

  // 카테고리 등록
  private AdminCategoryResponse getAdminCategoryResponse(AdminCategoryRequest request,
      Category parent) {
    Category category = Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .parent(parent)
        .build();

    Category sevedCategory = categoryRepository.save(category);

    return AdminCategoryResponse.builder().id(sevedCategory.getId()).build();
  }

  // 부모 카테고리 조회
  private Category getParentCategory(AdminCategoryRequest request) {
    Category parent = null;
    if (request.getParentId() != null && 0 < request.getParentId().intValue()) {
      parent = getCategory(request.getParentId());
    }
    return parent;
  }

  // 카테고리 수정
  @Transactional
  public AdminCategoryUpdateResponse updateAdminCategory(Long categoryId,
      AdminCategoryRequest request) {

    // 부모 카테고리 검증 및 조회
    Category parent = validateAndGetParentCategory(categoryId, request);

    // 카테고리 수정
    return getAdminCategoryUpdateResponse(categoryId, request, parent);
  }

  // 카테고리 수정
  private AdminCategoryUpdateResponse getAdminCategoryUpdateResponse(Long categoryId,
      AdminCategoryRequest request, Category parent) {
    Category category = getCategory(categoryId);

    category.setName(request.getName());
    category.setParent(parent);
    category.setDescription(request.getDescription());

    return AdminCategoryUpdateResponse.builder()
        .name(category.getName())
        .description(category.getDescription())
        .parentId(category.getParent() != null ? category.getParent().getId() : null)
        .build();
  }

  // 부모 카테고리 검증 및 조회
  private Category validateAndGetParentCategory(Long categoryId, AdminCategoryRequest request) {
    if (request.getParentId() != null && categoryId.longValue() == request.getParentId()
        .longValue()) {
      throw new ServiceException(ServiceExceptionCode.SELF_PARENT_NOT_ALLOWED);
    }

    if (request.getParentId() != null && 0 < request.getParentId().intValue()) {
      return getCategory(request.getParentId());
    }
    return null;
  }

  // 카테 고리 삭제
  @Transactional
  public void deleteAdminCategory(Long categoryId) {

    // 카테고리 조회
    Category categoryToDelete = getCategory(categoryId);

    // 하위 카테고리 존재 여부 검증
    validateNoSubcategories(categoryToDelete);

    // 카테고리에 속한 상품 존재 여부 검증
    validateNoAssociatedProducts(categoryToDelete);

    // 카테고리 삭제
    categoryRepository.deleteById(categoryId);
  }

  // 하위 카테고리 존재 여부 검증
  private void validateNoSubcategories(Category category) {
    if (!category.getChildren().isEmpty()) { // getChildren()은 이미 초기화된 컬렉션이므로 NPE 걱정 없음
      throw new ServiceException(ServiceExceptionCode.SUBCATEGORY_EXISTS);
    }
  }

  // 카테고리에 속한 상품 존재 여부 검증
  private void validateNoAssociatedProducts(Category category) {
    List<CategoryProductDTO> findCategoryProducts = categoryProductQueryRepository.findCategoryProductsByCategoryId(
        category.getId());

    if (!findCategoryProducts.isEmpty()) {
      throw new ServiceException(ServiceExceptionCode.HAS_ASSOCIATED_PRODUCTS);
    }
  }
}
