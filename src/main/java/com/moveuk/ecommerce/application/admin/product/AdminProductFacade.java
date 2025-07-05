package com.moveuk.ecommerce.application.admin.product;

import com.moveuk.ecommerce.application.admin.product.request.AdminProductInfo;
import com.moveuk.ecommerce.domain.category.Category;
import com.moveuk.ecommerce.domain.category.admin.AdminCategoryService;
import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.product.ProductInventory;
import com.moveuk.ecommerce.domain.product.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminProductFacade {

    private final AdminProductService adminProductService;
    private final AdminCategoryService adminCategoryService;

    public Map createProduct(AdminProductInfo.ProductRegisterV1 productRegisterV1) {

        Category category = adminCategoryService.findById(productRegisterV1.categoryId());

        Product product = Product.builder()
                .name(productRegisterV1.name())
                .description(productRegisterV1.description())
                .category(category)
                .price(BigDecimal.valueOf(productRegisterV1.price())).build();

        adminProductService.createProduct(product);

        ProductInventory productInventory = ProductInventory.builder()
                .product(product)
                .stock(productRegisterV1.stock())
                .build();

        adminProductService.createProductInventory(productInventory);


        return null;
    }

    public Map updateProduct(AdminProductInfo.ProductRegisterV2 from) {
        return null;
    }

    public Map removeProduct(Long productId) {
        return null;
    }
}
