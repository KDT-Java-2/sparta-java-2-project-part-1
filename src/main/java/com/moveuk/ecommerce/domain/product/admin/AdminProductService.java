package com.moveuk.ecommerce.domain.product.admin;

import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.product.ProductInventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductRepository adminProductRepository;

    public void createProduct(Product product) {
    }

    public void createProductInventory(ProductInventory productInventory) {

    }
}
