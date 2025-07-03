package com.example.shoppingmall.domain.product.service;

import com.example.shoppingmall.common.exception.ServiceException;
import com.example.shoppingmall.common.exception.ServiceExceptionCode;
import com.example.shoppingmall.domain.product.dto.ProductDetailResponse;
import com.example.shoppingmall.domain.product.dto.ProductListResponse;
import com.example.shoppingmall.domain.product.entity.Product;
import com.example.shoppingmall.domain.product.mapper.ProductMapper;
import com.example.shoppingmall.domain.product.repository.ProductQueryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductQueryRepository productQueryRepository;
    private final ProductMapper productMapper;

    public Page<ProductListResponse> searchProducts(Long categoryId, Integer minPrice, Integer maxPrice, String sortBy, Pageable pageable) {
        return productQueryRepository.searchProducts(categoryId, minPrice, maxPrice, sortBy, pageable);
    }

    public ProductDetailResponse getProductDetail(Long productId) {
        Product product = productQueryRepository.findProductWithAllOptions(productId);
        if (product == null) {
            throw new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND);
        }
        return productMapper.toDetailResponse(product);
    }
}
