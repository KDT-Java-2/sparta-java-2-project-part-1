package com.scb.project.commerce.domain.product.service;

import com.scb.project.commerce.domain.product.dto.ProductSearchRequest;
import com.scb.project.commerce.domain.product.dto.ProductSearchResponse;
import com.scb.project.commerce.domain.product.repository.ProductQueryRepository;
import com.scb.project.commerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

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
}
