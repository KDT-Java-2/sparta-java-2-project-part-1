package com.sparta.bootcamp.java_2_example.domain.product.service;

import com.sparta.bootcamp.java_2_example.common.exception.ServiceException;
import com.sparta.bootcamp.java_2_example.common.exception.ServiceExceptionCode;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductResponse;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductSearchRequest;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductSearchResponse;
import com.sparta.bootcamp.java_2_example.domain.product.entity.Product;
import com.sparta.bootcamp.java_2_example.domain.product.mapper.ProductMapper;
import com.sparta.bootcamp.java_2_example.domain.product.repository.ProductQueryRepository;
import com.sparta.bootcamp.java_2_example.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;

    @Transactional(readOnly = true)
    public Page<ProductSearchResponse> getAll(ProductSearchRequest request, Pageable pageable) {
        return productQueryRepository.findAll(request, pageable);
    }

    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        return productMapper.toResponse(product);
    }
}
