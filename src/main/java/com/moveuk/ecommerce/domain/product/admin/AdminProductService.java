package com.moveuk.ecommerce.domain.product.admin;

import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.product.ProductInventory;
import com.moveuk.ecommerce.domain.purchase.PurchaseItem;
import com.moveuk.ecommerce.support.EcommerceErrorCode;
import com.moveuk.ecommerce.support.EcommerceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductRepository adminProductRepository;

    public void createProduct(Product product) {

        if(adminProductRepository.existsByName(product.getName())){
            throw new EcommerceException(EcommerceErrorCode.DUPLICATE_PRODUCT_NAME);
        }
        adminProductRepository.createProduct(product);
    }

    public void createProductInventory(ProductInventory productInventory) {
        adminProductRepository.createProductInventory(productInventory);
    }

    public void updateProduct(Product product) {

        if(adminProductRepository.existsByName(product.getName())){
            throw new EcommerceException(EcommerceErrorCode.DUPLICATE_PRODUCT_NAME);
        }

        adminProductRepository.updateProduct(product);
    }

    public void updateProductInventory(ProductInventory productInventory) {
        adminProductRepository.updateProductInventory(productInventory);
    }

    public Product findById(Long productId) {
        return adminProductRepository.findById(productId).orElseThrow(() ->
                new EcommerceException(EcommerceErrorCode.NOT_FOUND_PRODUCT));
    }

    public String isPurchaseStatus(PurchaseItem purchaseItem) {
        return purchaseItem.getPurchase().getStatus();
    }

    public boolean hasProductInCategory(long categoryId) {
        return adminProductRepository.existsByCategoryId(categoryId);
    }
}
