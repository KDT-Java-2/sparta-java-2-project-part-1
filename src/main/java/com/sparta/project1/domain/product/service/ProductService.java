package com.sparta.project1.domain.product.service;

import com.sparta.project1.common.exception.ServiceException;
import com.sparta.project1.common.exception.ServiceExceptionCode;
import com.sparta.project1.domain.product.dto.ProductDetailRequest;
import com.sparta.project1.domain.product.dto.ProductDetailResponse;
import com.sparta.project1.domain.product.entity.Product;
import com.sparta.project1.domain.product.mapper.ProductMapper;
import com.sparta.project1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public ProductDetailResponse productDetailSearch(Long productId) {
        //productId 기준 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        //Response <-> Entity 매핑
        return productMapper.toResponse(product);
    }
}
