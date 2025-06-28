package com.scb.project.commerce.domain.product.controller;

import com.scb.project.commerce.common.response.ApiResponse;
import com.scb.project.commerce.domain.product.dto.ProductSearchRequest;
import com.scb.project.commerce.domain.product.dto.ProductSearchResponse;
import com.scb.project.commerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 목록 조회 API
     *
     * <p>등록된 상품들을 카테고리, 가격, 페이지, 정렬 조건에 따라 검색합니다.</p>
     * <p>QueryDSL 기반으로 구현되며, 결과는 페이징 형태로 반환됩니다.</p>
     *
     * @param request 상품 검색 조건 및 페이지 요청 정보
     * @return 페이징된 상품 목록이 포함된 공통 응답 객체
     */
    @GetMapping
    public ApiResponse<Page<ProductSearchResponse>> findByProductList(
        @ModelAttribute ProductSearchRequest request
    ) {
        return ApiResponse.success(productService.searchProducts(request));
    }
}
