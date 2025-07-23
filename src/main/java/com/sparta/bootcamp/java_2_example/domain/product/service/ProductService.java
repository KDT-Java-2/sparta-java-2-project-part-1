package com.sparta.bootcamp.java_2_example.domain.product.service;

import com.sparta.bootcamp.java_2_example.common.exception.ServiceException;
import com.sparta.bootcamp.java_2_example.common.exception.ServiceExceptionCode;
import com.sparta.bootcamp.java_2_example.domain.category.entity.Category;
import com.sparta.bootcamp.java_2_example.domain.category.repository.CategoryRepository;
import com.sparta.bootcamp.java_2_example.domain.product.dto.*;
import com.sparta.bootcamp.java_2_example.domain.product.entity.Product;
import com.sparta.bootcamp.java_2_example.domain.product.mapper.ProductMapper;
import com.sparta.bootcamp.java_2_example.domain.product.repository.ProductQueryRepository;
import com.sparta.bootcamp.java_2_example.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductSearchResponse> getAll(ProductSearchRequest request, Pageable pageable) {
        return productQueryRepository.findAll(request, pageable);
    }

    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        return productMapper.toResponse(product);
    }

    @Transactional
    public ProductCreateResponse save(ProductCreateRequest request) {
        ensureCategoryExists(request.getCategoryId());

        ensureNameUnique(request.getName());

        Product product = productRepository.save(productMapper.toEntity(request));
        return productMapper.toCreateResponse(product);
    }

    @Transactional
    public ProductResponse update(Long productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        if (!product.getName().equals(request.getName())) {
            ensureNameUnique(request.getName());
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        product.setCategory(category);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(BigDecimal.valueOf(request.getPrice()));
        product.setStock(request.getStock());
        product.setUpdatedAt(LocalDateTime.now());

        return productMapper.toResponse(product);
    }

    private void ensureCategoryExists(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY);
        }
    }

    private void ensureNameUnique(String name) {
        if (productRepository.existsByName(name)) {
            throw new ServiceException(ServiceExceptionCode.ALREADY_EXISTS_PRODUCT_NAME);
        }
    }
}
