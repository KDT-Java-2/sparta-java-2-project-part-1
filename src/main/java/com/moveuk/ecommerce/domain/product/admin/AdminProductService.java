package com.moveuk.ecommerce.domain.product.admin;

import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.product.ProductInventory;
import com.moveuk.ecommerce.domain.purchase.PurchaseItem;
import jakarta.persistence.EntityNotFoundException;
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

    public void updateProduct(Product product) {
    }

    public void updateProductInventory(ProductInventory productInventory) {
    }

    public Product findById(Long productId) {
        return adminProductRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public String isPurchaseStatus(PurchaseItem purchaseItem) {
        return purchaseItem.getPurchase().getStatus();
    }
}
