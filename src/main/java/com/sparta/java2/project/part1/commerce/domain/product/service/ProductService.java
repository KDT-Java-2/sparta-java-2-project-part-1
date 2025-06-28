package com.sparta.java2.project.part1.commerce.domain.product.service;

import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductSearchRequest;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductSearchResponse;
import com.sparta.java2.project.part1.commerce.domain.product.repository.ProductQueryRepository;
import com.sparta.java2.project.part1.commerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductQueryRepository productQueryRepository;

    public Page<ProductSearchResponse> search(ProductSearchRequest productSearchRequest, Pageable pageable) {
        return productQueryRepository.search(productSearchRequest, pageable);
    }
}
