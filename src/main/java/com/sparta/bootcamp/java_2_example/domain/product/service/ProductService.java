package com.sparta.bootcamp.java_2_example.domain.product.service;

import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductSearchRequest;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductSearchResponse;
import com.sparta.bootcamp.java_2_example.domain.product.repository.ProductQueryRepository;
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
    public Page<ProductSearchResponse> getAll(ProductSearchRequest request, Pageable pageable) {
        return productQueryRepository.findAll(request, pageable);
    }
}
