package com.sparta.bootcamp.shop.domain.product.service;

import com.sparta.bootcamp.shop.common.exception.ServiceException;
import com.sparta.bootcamp.shop.common.exception.ServiceExceptionCode;
import com.sparta.bootcamp.shop.domain.category.dto.CategoryResponse;
import com.sparta.bootcamp.shop.domain.category.entity.Category;
import com.sparta.bootcamp.shop.domain.category.repository.CategoryRepository;
import com.sparta.bootcamp.shop.domain.product.dto.ProductRequest;
import com.sparta.bootcamp.shop.domain.product.dto.ProductResponse;
import com.sparta.bootcamp.shop.domain.product.dto.ProductSearchRequest;
import com.sparta.bootcamp.shop.domain.product.entity.Product;
import com.sparta.bootcamp.shop.domain.product.repository.ProductQueryRepository;
import com.sparta.bootcamp.shop.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductQueryRepository productQueryRepository;

    @Transactional
    public Page<ProductResponse> getAll(ProductSearchRequest searchRequest, Pageable pageable) {
        Page<ProductResponse> result = productQueryRepository.findProducts(
                searchRequest.getCategoryId(),
                searchRequest.getMinPrice(),
                searchRequest.getMaxPrice(),
                pageable
        );
        return result;
    }

    @Transactional
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(product.getCategory().getId())
                .name(product.getCategory().getName())
                .build();

        return ProductResponse.builder()
                .id(product.getId())
                .categoryResponse(categoryResponse)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .build();
    }

    @Transactional
    public void save(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        productRepository.save(Product.builder()
                .category(category)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build());
    }

}