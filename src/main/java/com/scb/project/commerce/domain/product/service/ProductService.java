package com.scb.project.commerce.domain.product.service;

import com.scb.project.commerce.common.exception.ServiceException;
import com.scb.project.commerce.common.exception.ServiceExceptionCode;
import com.scb.project.commerce.domain.product.dto.ProductResponse;
import com.scb.project.commerce.domain.product.dto.ProductSearchRequest;
import com.scb.project.commerce.domain.product.dto.ProductSearchResponse;
import com.scb.project.commerce.domain.product.entity.Product;
import com.scb.project.commerce.domain.product.mapper.ProductMapper;
import com.scb.project.commerce.domain.product.repository.ProductQueryRepository;
import com.scb.project.commerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;

    /**
     * 상품 목록 조회 서비스
     *
     * <p>QueryDSL을 사용하여 조건에 맞는 상품 목록을 페이징 및 정렬하여 조회합니다.</p>
     *
     * @param request 상품 조회 조건 및 페이지 요청 정보
     * @return 조건에 맞는 상품 목록 Page 객체
     */
    @Transactional(readOnly = true)
    public Page<ProductSearchResponse> searchProducts(ProductSearchRequest request) {

        return productQueryRepository.findPagedProducts(request, request.toPageable());
    }

    /**
     * 상품 상세 조회 서비스
     *
     * <p>상품 ID를 기반으로 하나의 상품 정보를 조회합니다.</p>
     * <p>조회된 상품 정보를 ProductResponse 형태로 변환하여 반환합니다.</p>
     *
     * @param productId 조회할 상품의 고유 ID
     * @return 상품 상세 정보를 담은 ProductResponse 객체
     * @throws ServiceException 존재하지 않는 상품인 경우 NOT_FOUND_PRODUCT 예외 발생
     */
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long productId) {
        return productMapper.toResponse(getProduct(productId));
    }

    // 상품 아이디로 상품 객체 받아오는 메서드
    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
    }
}
