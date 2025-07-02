package com.sparta.spartajava2projectpart1.domain.product.service;

import com.sparta.spartajava2projectpart1.common.exception.ServiceException;
import com.sparta.spartajava2projectpart1.common.exception.ServiceExceptionCode;
import com.sparta.spartajava2projectpart1.domain.category.entity.Category;
import com.sparta.spartajava2projectpart1.domain.category.repository.CategoryRepository;
import com.sparta.spartajava2projectpart1.domain.product.dto.CategoryCreateRequest;
import com.sparta.spartajava2projectpart1.domain.product.dto.CategoryUpdateRequest;
import com.sparta.spartajava2projectpart1.domain.product.dto.ProductMapper;
import com.sparta.spartajava2projectpart1.domain.product.dto.ProductSearchResponse;
import com.sparta.spartajava2projectpart1.domain.product.entity.Product;
import com.sparta.spartajava2projectpart1.domain.product.repository.ProductQueryRepository;
import com.sparta.spartajava2projectpart1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper mapper;
    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;
    private final CategoryRepository categoryRepository;

    public Page<ProductSearchResponse> findAll(
            Long categoryId,
            Integer minPrice,
            Integer maxPrice,
            Pageable pageable
    ) {
        return productQueryRepository.findProducts(categoryId, minPrice, maxPrice, pageable).map(
                mapper::toResponse
        );
    }

    @Transactional(readOnly = true)
    public ProductSearchResponse findProductDetails(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));
        return mapper.toResponse(product);
    }

    @Transactional
    public void addProduct(CategoryCreateRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(category)
                .build();

        productRepository.save(product);
    }

    @Transactional
    public ProductSearchResponse updateProduct(CategoryUpdateRequest request) {
        Product product = productRepository.findById(request.getCategoryId()).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));
        product.updateProduct(request);
        if (!product.getCategory().equals(category)) {
            product.changeCategory(category);
        }
        return mapper.toResponse(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));
        productRepository.delete(product);
    }
}
