package com.sparta.spartajava2projectpart1.domain.product.service;

import com.sparta.spartajava2projectpart1.common.exception.ServiceException;
import com.sparta.spartajava2projectpart1.common.exception.ServiceExceptionCode;
import com.sparta.spartajava2projectpart1.domain.product.dto.ProductMapper;
import com.sparta.spartajava2projectpart1.domain.product.dto.ProductSearchResponse;
import com.sparta.spartajava2projectpart1.domain.product.entity.Product;
import com.sparta.spartajava2projectpart1.domain.product.repository.ProductQueryRepository;
import com.sparta.spartajava2projectpart1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper mapper;
    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;

    public Page<ProductSearchResponse> findAll(
            Long categoryId,
            Integer minPrice,
            Integer maxPrice,
            Pageable pageable
    ) {
        return productQueryRepository.findProducts(categoryId, minPrice, maxPrice, pageable).map(
                mapper::toResponse
        );
    }

    public ProductSearchResponse findProductDetails(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA));
        return mapper.toResponse(product);
    }
}
