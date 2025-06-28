package com.sparta.java_02.domain.product;

import com.sparta.java_02.domain.category.Category;
import com.sparta.java_02.domain.category.CategoryRepository;
import com.sparta.java_02.domain.product.dto.ProductCreateRequest;
import com.sparta.java_02.domain.product.dto.ProductResponse;
import com.sparta.java_02.global.exception.BusinessException;
import com.sparta.java_02.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 상품 상세 조회
     */
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
        
        return ProductResponse.from(product);
    }

    /**
     * 상품 목록 조회 (검색, 필터링, 페이징)
     */
    public Page<ProductResponse> getProducts(Long categoryId, Integer minPrice, Integer maxPrice, Pageable pageable) {
        // QueryDSL을 사용한 동적 쿼리는 나중에 구현
        // 일단 기본 조회로 구현
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductResponse::from);
    }

    /**
     * 상품 등록 (관리자)
     */
    @Transactional
    public Long createProduct(ProductCreateRequest request) {
        // 상품명 중복 검증
        if (productRepository.existsByName(request.getName())) {
            throw new BusinessException(ErrorCode.DUPLICATE_PRODUCT_NAME);
        }

        // 카테고리 존재 검증
        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));

        // 상품 생성
        Product product = new Product(
            request.getName(),
            request.getDescription(),
            request.getPrice(),
            request.getStock(),
            category
        );

        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    /**
     * 상품 수정 (관리자)
     */
    @Transactional
    public ProductResponse updateProduct(Long productId, ProductCreateRequest request) {
        // 상품 존재 검증
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

        // 상품명 중복 검증 (자기 자신 제외)
        if (productRepository.existsByNameAndIdNot(request.getName(), productId)) {
            throw new BusinessException(ErrorCode.DUPLICATE_PRODUCT_NAME);
        }

        // 카테고리 존재 검증
        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));

        // 상품 정보 수정
        product.updateInfo(request.getName(), request.getDescription(), request.getPrice(), category);
        product.updateStock(request.getStock());

        return ProductResponse.from(product);
    }

    /**
     * 상품 삭제 (관리자)
     */
    @Transactional
    public void deleteProduct(Long productId) {
        // 상품 존재 검증
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

        // 주문 완료된 상품인지 검증 (Purchase 테이블 확인)
        // 이 부분은 나중에 Purchase 도메인 완성 후 구현
        
        productRepository.delete(product);
    }
} 