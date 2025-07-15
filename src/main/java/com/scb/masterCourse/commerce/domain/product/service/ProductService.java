package com.scb.masterCourse.commerce.domain.product.service;

import com.scb.masterCourse.commerce.domain.product.dto.ProductQueryRequest;
import com.scb.masterCourse.commerce.domain.product.dto.ProductQueryResponse;
import com.scb.masterCourse.commerce.domain.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductQueryRepository productQueryRepository;

    @Transactional(readOnly = true)
    public Page<ProductQueryResponse> findPagedProducts(ProductQueryRequest request, Pageable pageable) {
        return productQueryRepository.findPagedProducts(request, pageable);
    }
}
