package com.moveuk.ecommerce.domain.product;

import com.moveuk.ecommerce.support.EcommerceErrorCode;
import com.moveuk.ecommerce.support.EcommerceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> searchProducts(Long id, Integer minPrice, Integer maxPrice, Pageable pageable) {
        return productRepository.searchProducts(id, minPrice, maxPrice, pageable);
    }

    public ProductInventory getProductInventory(long productId) {
        return productRepository.getProductInventory(productId)
                .orElseThrow(() -> new EcommerceException(EcommerceErrorCode.NOT_FOUND_PRODUCT));
    }

    public Product getProductById(long productId) {
        return productRepository.getProduct(productId)
                .orElseThrow(() -> new EcommerceException(EcommerceErrorCode.NOT_FOUND_PRODUCT));
    }
}
