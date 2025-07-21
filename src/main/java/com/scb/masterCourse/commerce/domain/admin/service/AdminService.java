package com.scb.masterCourse.commerce.domain.admin.service;

import com.scb.masterCourse.commerce.common.enums.ProductStatus;
import com.scb.masterCourse.commerce.common.exception.ServiceException;
import com.scb.masterCourse.commerce.common.exception.ServiceExceptionCode;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminCategoryRequest;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminCategoryResponse;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminProductRequest;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminProductResponse;
import com.scb.masterCourse.commerce.domain.admin.mapper.AdminProductMapper;
import com.scb.masterCourse.commerce.domain.admin.repository.AdminProductQueryRepository;
import com.scb.masterCourse.commerce.domain.brand.entity.Brand;
import com.scb.masterCourse.commerce.domain.brand.repository.BrandRepository;
import com.scb.masterCourse.commerce.domain.category.entity.Category;
import com.scb.masterCourse.commerce.domain.category.repository.CategoryRepository;
import com.scb.masterCourse.commerce.domain.product.entity.Product;
import com.scb.masterCourse.commerce.domain.product.repository.ProductRepository;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final AdminProductMapper adminProductMapper;

    private final AdminProductQueryRepository adminProductQueryRepository;

    @Transactional
    public AdminProductResponse createProduct(@Valid AdminProductRequest request) {
        boolean isExistsProductName = productRepository.existsByName(request.getName());
        if (isExistsProductName) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
        }

        Brand brand = getBrandOrThrow(request.getBrandId());

        Category category = getCategoryOrThrow(request.getCategoryId());

        Product product = Product.builder()
            .brand(brand)
            .category(category)
            .name(request.getName())
            .price(BigDecimal.valueOf(request.getPrice()))
            .stock(request.getStock())
            .status(ProductStatus.ON_SALE)  // Product의 status는 ON_SALE이 기본값
            .build();

        Product savedProduct = productRepository.save(product);

        return adminProductMapper.toResponse(savedProduct);
    }

    @Transactional
    public AdminProductResponse updateProduct(Long productId, @Valid AdminProductRequest request) {

        boolean isDuplicate = productRepository.existsByNameAndIdNot(request.getName(), productId);
        if (isDuplicate) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
        }

        Brand brand = getBrandOrThrow(request.getBrandId());

        Category category = getCategoryOrThrow(request.getCategoryId());

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        product.update(request.getName(), brand, category, request.getDescription(), request.getPrice(),
            request.getStock());

        return adminProductMapper.toResponse(product);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        boolean hasCompleted = adminProductQueryRepository.countCompletedPurchasesByProductId(productId) > 0;
        if (hasCompleted) {
            throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_COMPLETED_PRODUCT);
        }

        productRepository.delete(product);
    }

    @Transactional
    public Long createCategory(AdminCategoryRequest request) {
        Category parent = request.getParentId() == null ? null :
            getCategoryOrThrow(request.getParentId());

        Category category = Category.builder()
            .name(request.getName())
            .parent(parent)
            .build();

        return categoryRepository.save(category)
            .getId();
    }

    @Transactional
    public AdminCategoryResponse updateCategory(Long categoryId, AdminCategoryRequest request) {
        Category category = getCategoryOrThrow(categoryId);

        if (ObjectUtils.isEmpty(category)) {
            throw new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY);
        }

        if (category.getId()
            .equals(request.getParentId())) {
            throw new ServiceException(ServiceExceptionCode.INVALID_SELF_PARENT);
        }

        Category parent = request.getParentId() != null ? getCategoryOrThrow(request.getParentId()) : null;

        category.update(request.getName(), parent);

        return AdminCategoryResponse.builder()
            .categoryId(category.getId())
            .name(category.getName())
            .parentName(Objects.isNull(parent) ? null : parent.getName())
            .build();
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = getCategoryOrThrow(categoryId);

        boolean hasChildren = categoryRepository.existsByParent(category);
        if (hasChildren) {
            throw new ServiceException(ServiceExceptionCode.HAS_CHILD_CATEGORY);
        }

        boolean hasProduct = productRepository.existsByCategory(category);
        if (hasProduct) {
            throw new ServiceException(ServiceExceptionCode.HAS_PRODUCTS);
        }

        categoryRepository.delete(category);
    }


    private Brand getBrandOrThrow(Long brandId) {
        return brandRepository.findById(brandId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_BRAND));
    }

    private Category getCategoryOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }
}
