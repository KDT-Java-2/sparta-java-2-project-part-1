package com.scb.masterCourse.commerce.domain.admin.service;

import com.scb.masterCourse.commerce.common.enums.ProductStatus;
import com.scb.masterCourse.commerce.common.exception.ServiceException;
import com.scb.masterCourse.commerce.common.exception.ServiceExceptionCode;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminProductRequest;
import com.scb.masterCourse.commerce.domain.admin.dto.AdminProductResponse;
import com.scb.masterCourse.commerce.domain.admin.mapper.AdminProductMapper;
import com.scb.masterCourse.commerce.domain.brand.entity.Brand;
import com.scb.masterCourse.commerce.domain.brand.repository.BrandRepository;
import com.scb.masterCourse.commerce.domain.category.entity.Category;
import com.scb.masterCourse.commerce.domain.category.repository.CategoryRepository;
import com.scb.masterCourse.commerce.domain.product.entity.Product;
import com.scb.masterCourse.commerce.domain.product.repository.ProductRepository;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final AdminProductMapper adminProductMapper;

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


    private Brand getBrandOrThrow(Long brandId) {
        return brandRepository.findById(brandId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_BRAND));
    }

    private Category getCategoryOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }
}
