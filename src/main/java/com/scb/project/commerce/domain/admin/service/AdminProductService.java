package com.scb.project.commerce.domain.admin.service;

import com.scb.project.commerce.common.exception.ServiceException;
import com.scb.project.commerce.common.exception.ServiceExceptionCode;
import com.scb.project.commerce.domain.admin.dto.AdminProductCreateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminProductCreateResponse;
import com.scb.project.commerce.domain.admin.mapper.AdminProductMapper;
import com.scb.project.commerce.domain.category.entity.Category;
import com.scb.project.commerce.domain.category.repository.CategoryRepository;
import com.scb.project.commerce.domain.product.entity.Product;
import com.scb.project.commerce.domain.product.repository.ProductRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final AdminProductMapper adminProductMapper;

    /**
     * 관리자 상품 등록 서비스
     *
     * <p>상품명 중복, 음수값, 존재하지 않는 카테고리 여부를 검증한 뒤</p>
     * <p>요청 정보를 상품 엔티티로 변환하여 저장하고 상품 ID를 반환합니다.</p>
     *
     * @param request 상품 등록 요청 DTO
     * @return 등록된 상품 ID 응답 DTO
     */
    @Transactional
    public AdminProductCreateResponse createProduct(AdminProductCreateRequest request) {

        // 상품 가격, 재고 수량이 0 이상의 정수인지 확인
        if (request.getPrice()
            .compareTo(BigDecimal.ZERO) < 0 || request.getStock() < 0) {
            throw new ServiceException(ServiceExceptionCode.INVALID_NEGATIVE_VALUE);
        }

        // 상품명이 고유한지 확인
        boolean isExistsProductName = productRepository.existsByProductName(request.getName());
        if (isExistsProductName) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
        }

        // 카테고리 정보를 가져오므로 존재하는지 확인 가능
        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        Product savedProduct = productRepository.save(
            adminProductMapper.toEntity(request, category));

        return AdminProductCreateResponse.builder()
            .productId(savedProduct.getId())
            .build();
    }
}
