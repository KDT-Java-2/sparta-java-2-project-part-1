package com.sparta.java2.project.part1.commerce.domain.product.service;

import com.sparta.java2.project.part1.commerce.common.exception.ServiceException;
import com.sparta.java2.project.part1.commerce.common.exception.ServiceExceptionCode;
import com.sparta.java2.project.part1.commerce.common.util.QueryDslUtil;
import com.sparta.java2.project.part1.commerce.domain.category.entity.Category;
import com.sparta.java2.project.part1.commerce.domain.category.repository.CategoryRepository;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductCreateResponse;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductSearchRequest;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductSearchResponse;
import com.sparta.java2.project.part1.commerce.domain.product.entity.Product;
import com.sparta.java2.project.part1.commerce.domain.product.mapper.ProductMapper;
import com.sparta.java2.project.part1.commerce.domain.product.repository.ProductQueryRepository;
import com.sparta.java2.project.part1.commerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductMapper productMapper;
    private ProductQueryRepository productQueryRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    // User API
    public Page<ProductSearchResponse> search(ProductSearchRequest productSearchRequest) {
        Sort sort = QueryDslUtil.toSort(productSearchRequest.getSortBy());
        Pageable pageable = PageRequest.of(
                productSearchRequest.getPage(),
                productSearchRequest.getSize(),
                sort);
        return productQueryRepository.search(productSearchRequest, pageable);
    }

    // Admin API
    public ProductCreateResponse createProduct(ProductCreateRequest productCreateRequest) {
        /*
- **기능 요구사항**:
    - `price`와 `stock`은 음수일 수 없습니다.
    - `categoryId`에 해당하는 카테고리가 실제로 존재하는지 검증해야 합니다.
    - 상품명(`name`) 중복 검증 로직이 필요합니다.
- **응답 (Response)**:
    - **성공** : 생성된 상품의 ID를 반환합니다.
        ```json
        { "result": true, "message": { "productId": 11 }, "error": {} }
        ```
    - **실패** : 필수 필드가 누락되거나 유효성 검사(가격/재고가 음수)에 실패한 경우.
    - **실패** : 요청한 `categoryId`가 존재하지 않는 경우.
         */

        // Name 중복검증
        if (productRepository.findByName(productCreateRequest.getName()).isPresent())
            throw new ServiceException(ServiceExceptionCode.ALREADY_EXIST_PRODUCT_NAME);

        // Category 유효성 검증
        Category category = categoryRepository.findById(productCreateRequest.getCategoryId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        return ProductCreateResponse.builder()
                .productId(
                        productRepository.save(
                                Product.builder()
                                        .name(productCreateRequest.getName())
                                        .description(productCreateRequest.getDescription())
                                        .price(BigDecimal.valueOf(productCreateRequest.getPrice()))
                                        .stock(productCreateRequest.getStock())
                                        .category(category)
                                        .build()
                                ).getId())
                .build();
    }
}
