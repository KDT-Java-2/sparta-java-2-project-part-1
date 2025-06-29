package com.scb.project.commerce.domain.admin.service;

import com.scb.project.commerce.common.exception.ServiceException;
import com.scb.project.commerce.common.exception.ServiceExceptionCode;
import com.scb.project.commerce.domain.admin.dto.AdminProductCreateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminProductCreateResponse;
import com.scb.project.commerce.domain.admin.dto.AdminProductUpdateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminProductUpdateResponse;
import com.scb.project.commerce.domain.admin.mapper.AdminProductMapper;
import com.scb.project.commerce.domain.category.entity.Category;
import com.scb.project.commerce.domain.category.repository.CategoryRepository;
import com.scb.project.commerce.domain.product.entity.Product;
import com.scb.project.commerce.domain.product.repository.ProductRepository;
import com.scb.project.commerce.domain.purchase.repository.PurchaseProductQueryRepository;
import com.scb.project.commerce.domain.purchase.repository.PurchaseProductRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PurchaseProductRepository purchaseProductRepository;

    private final AdminProductMapper adminProductMapper;

    private final PurchaseProductQueryRepository purchaseProductQueryRepository;

    /**
     * 관리자 상품 등록 서비스
     *
     * <p>상품명 중복, 음수값, 존재하지 않는 카테고리 여부를 검증한 뒤</p>
     * <p>요청 정보를 상품 엔티티로 변환하여 저장하고 상품 ID를 반환합니다.</p>
     *
     * @param request 상품 등록 요청 DTO
     * @return 등록된 상품 ID 응답 DTO
     * @throws ServiceException 상품 가격, 재고 수량이 음수이거나 상품명이 중복되는 경우
     */
    @Transactional
    public AdminProductCreateResponse createProduct(AdminProductCreateRequest request) {

        // 상품 가격, 재고 수량이 0 이상의 정수인지 확인
        if (request.getPrice()
            .compareTo(BigDecimal.ZERO) < 0 || request.getStock() < 0) {
            throw new ServiceException(ServiceExceptionCode.INVALID_NEGATIVE_VALUE);
        }

        // 상품명이 중복인지 확인
        boolean isExistsProductName = productRepository.existsByProductName(request.getName());
        if (isExistsProductName) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
        }

        // 카테고리 정보를 가져오므로 존재하는지 확인 가능
        Category category = getCategory(request.getCategoryId());

        Product savedProduct = productRepository.save(
            adminProductMapper.toEntity(request, category));

        return AdminProductCreateResponse.builder()
            .productId(savedProduct.getId())
            .build();
    }

    /**
     * 관리자 상품 수정 서비스
     *
     * <p>요청된 상품 ID에 해당하는 상품을 조회한 후, 요청된 필드가 null이 아닌 항목만 선별적으로 업데이트합니다.
     * <br>카테고리 ID가 null인 경우 기존 카테고리를 유지하며, 이름 중복 체크도 포함됩니다.
     *
     * @param productId 수정할 상품의 ID
     * @param request   관리자 상품 수정 요청 DTO
     * @return 수정된 상품 정보를 담은 응답 DTO
     * @throws ServiceException 상품명이 중복되거나, 카테고리가 존재하지 않는 경우
     */
    @Transactional
    public AdminProductUpdateResponse updateProduct(Long productId,
        AdminProductUpdateRequest request) {

        // 상품명 중복인지 확인
        boolean isExistsProductName = productRepository.existsByProductName(request.getName());
        if (isExistsProductName) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
        }

        // 상품 조회
        Product product = getProduct(productId);

        // 카테고리 분기 처리
        Category category = request.getCategoryId() == null ? getCategory(product.getCategory()
            .getId()) : getCategory(request.getCategoryId());

        // 상품 정보 업데이트
        product.updateProductInfo(request, category);

        return adminProductMapper.toResponse(product, category);
    }

    /**
     * 관리자 상품 삭제 서비스
     *
     * <p>해당 상품이 '배송중(SHIPPED)' 또는 '배송완료(DELIVERED)' 상태의 주문에 포함된 경우
     * 삭제가 불가능하며 예외를 발생시킵니다.
     *
     * @param productId 삭제할 상품 ID
     * @throws ServiceException 존재하지 않는 상품이거나 배송 관련 상태로 삭제가 불가능한 경우
     */
    @Transactional
    public void deleteProduct(Long productId) {

        // 주문이 완료(배송중, 배송완료)된 상품이 존재하는지 체크
        boolean isCompleted = purchaseProductQueryRepository.existsCompletedProduct(productId);
        if (isCompleted) {
            throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_COMPLETED_PRODUCT);
        }

        productRepository.delete(getProduct(productId));
    }


    // 상품 정보 가져오는 메서드
    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
    }

    // 카테고리 정보 가져오는 메서드
    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }
}
