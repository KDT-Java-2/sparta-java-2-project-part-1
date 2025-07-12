package com.sparta.bootcamp.shop.domain.product.service;

import com.sparta.bootcamp.shop.common.exception.ServiceException;
import com.sparta.bootcamp.shop.common.exception.ServiceExceptionCode;
import com.sparta.bootcamp.shop.domain.category.entity.Category;
import com.sparta.bootcamp.shop.domain.category.repository.CategoryRepository;
import com.sparta.bootcamp.shop.domain.product.dto.ProductRequest;
import com.sparta.bootcamp.shop.domain.product.entity.Product;
import com.sparta.bootcamp.shop.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void save(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        productRepository.save(Product.builder()
                .category(category)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build());
    }

    @Transactional
    public void edit(Long productId, ProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(category);

    }

    @Transactional
    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
        productRepository.delete(product);
    }
}
