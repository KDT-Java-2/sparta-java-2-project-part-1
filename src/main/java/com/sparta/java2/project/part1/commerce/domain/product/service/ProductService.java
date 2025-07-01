package com.sparta.java2.project.part1.commerce.domain.product.service;

import com.sparta.java2.project.part1.commerce.common.enums.PurchaseStatus;
import com.sparta.java2.project.part1.commerce.common.exception.ServiceException;
import com.sparta.java2.project.part1.commerce.common.exception.ServiceExceptionCode;
import com.sparta.java2.project.part1.commerce.common.util.QueryDslUtil;
import com.sparta.java2.project.part1.commerce.domain.category.entity.Category;
import com.sparta.java2.project.part1.commerce.domain.category.repository.CategoryRepository;
import com.sparta.java2.project.part1.commerce.domain.product.dto.*;
import com.sparta.java2.project.part1.commerce.domain.product.entity.Product;
import com.sparta.java2.project.part1.commerce.domain.product.mapper.ProductMapper;
import com.sparta.java2.project.part1.commerce.domain.product.repository.ProductQueryRepository;
import com.sparta.java2.project.part1.commerce.domain.product.repository.ProductRepository;
import com.sparta.java2.project.part1.commerce.domain.purchase.entity.Purchase;
import com.sparta.java2.project.part1.commerce.domain.purchase.repository.PurchaseProductRepository;
import com.sparta.java2.project.part1.commerce.domain.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductMapper productMapper;
    private ProductQueryRepository productQueryRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PurchaseProductRepository purchaseProductRepository;
    private PurchaseRepository purchaseRepository;

    // User API
    @Transactional(readOnly = true)
    public Page<ProductSearchResponse> search(ProductSearchRequest productSearchRequest) {
        Sort sort = QueryDslUtil.toSort(productSearchRequest.getSortBy());
        Pageable pageable = PageRequest.of(
                productSearchRequest.getPage(),
                productSearchRequest.getSize(),
                sort);
        return productQueryRepository.search(productSearchRequest, pageable);
    }

    // Admin API
    @Transactional
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

    @Transactional
    public ProductUpdateResponse updateProduct(Long productId, ProductCreateRequest productUpdateRequest) {
        Product updateInstance = productRepository.findById(productId)
                .orElseThrow(()-> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        Category category = categoryRepository.findById(productUpdateRequest.getCategoryId())
                .orElseThrow(()-> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        // duplication Name은 검증 안함?

        updateInstance.setName(productUpdateRequest.getName());
        updateInstance.setDescription(productUpdateRequest.getDescription());
        updateInstance.setPrice(BigDecimal.valueOf(productUpdateRequest.getPrice()));
        updateInstance.setStock(productUpdateRequest.getStock());
        updateInstance.setCategory(category);

        return ProductUpdateResponse.builder()
                .productId(updateInstance.getId())
                .name(updateInstance.getName())
                .description(updateInstance.getDescription())
                .price(updateInstance.getPrice().intValue())
                .stock(updateInstance.getStock())
                .category(category)
                .build();
    }

    @Transactional
    public void deleteProduct(Long productId) {
        /*
        - **(핵심 로직)** 삭제하려는 상품이 '주문 완료' 상태의 주문(`Order`)에 포함되어 있는지 확인해야 합니다.
- `Order`와 `OrderItem` 테이블을 조인하여, 해당 `productId`가 `status`가 'COMPLETED'인 `Order`에 있는지 검증하는 로직이 필요합니다.
- 만약 포함되어 있다면 삭제를 거부하고 에러를 반환해야 합니다.
         */

        // 주문완료상태 검증 -> 환불이나 다른 상태는 ??
        purchaseProductRepository.findAllByProduct_Id(productId)
                .forEach((purchaseProduct) -> {
                    Optional<Purchase> purchase = purchaseRepository.findById(purchaseProduct.getPurchase().getId());
                    if (purchase.isPresent() &&
                        purchase.get().getStatus().equals(PurchaseStatus.COMPLETED)
                    ) {
                        throw new ServiceException(ServiceExceptionCode.ALREADY_PURCHASED_PRODUCT);
                    }
                });

        productRepository.deleteById(productId);
    }
}
