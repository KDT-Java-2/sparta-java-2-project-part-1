package com.sparta.part_1.domain.product.service;


import com.sparta.part_1.common.exception.ProductErrorCode;
import com.sparta.part_1.common.exception.ProductServiceException;
import com.sparta.part_1.domain.product.dto.request.ProductSearchRequest;
import com.sparta.part_1.domain.product.dto.response.ProductResponse;
import com.sparta.part_1.domain.product.repository.ProductQueryRepository;
import com.sparta.part_1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;

    public Page<ProductResponse> findProducts(ProductSearchRequest request, Pageable pageable) {
        return productQueryRepository.searchProductsPage(request, pageable);
    }

    public List<ProductResponse> findProductById(Long productId) {
        List<ProductResponse> productResponses = productQueryRepository.searchProductById(productId);
        if (!hasProduct(productResponses)) {
            throw new ProductServiceException(ProductErrorCode.NOT_FOUND_PRODUCT_FOR_ID);
        }
        return productResponses;
    }

    private boolean hasProduct(List<ProductResponse> productResponses) {
        return productResponses != null && !productResponses.isEmpty();
    }

}
