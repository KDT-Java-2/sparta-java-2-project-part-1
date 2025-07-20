package com.moveuk.ecommerce.application.admin.product;

import com.moveuk.ecommerce.application.admin.product.request.AdminProductInfo;
import com.moveuk.ecommerce.application.admin.product.response.AdminProductResult;
import com.moveuk.ecommerce.domain.category.Category;
import com.moveuk.ecommerce.domain.category.admin.AdminCategoryService;
import com.moveuk.ecommerce.domain.product.Product;
import com.moveuk.ecommerce.domain.product.ProductInventory;
import com.moveuk.ecommerce.domain.product.admin.AdminProductService;
import com.moveuk.ecommerce.domain.purchase.admin.AdminPurchaseService;
import com.moveuk.ecommerce.support.EcommerceErrorCode;
import com.moveuk.ecommerce.support.EcommerceException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class AdminProductFacade {

    private final AdminProductService adminProductService;
    private final AdminCategoryService adminCategoryService;
    private final AdminPurchaseService adminPurchaseService;

    @Transactional
    public AdminProductResult.ProductRegisterV1 createProduct(AdminProductInfo.ProductRegisterV1 productRegisterV1) {

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

        return new AdminProductResult.ProductRegisterV1(product.getId());
    }

    @Transactional
    public AdminProductResult.ProductRegisterV2 updateProduct(AdminProductInfo.ProductRegisterV2 productRegisterV2) {
        Category category = adminCategoryService.findById(productRegisterV2.categoryId());

        Product product = adminProductService.findById(productRegisterV2.productId());

        product.updateDetail(productRegisterV2.name(), productRegisterV2.description()
                ,BigDecimal.valueOf(productRegisterV2.price()), category);

        adminProductService.updateProduct(product);

        ProductInventory productInventory = ProductInventory.builder()
                .product(product)
                .stock(productRegisterV2.stock())
                .build();

        adminProductService.updateProductInventory(productInventory);

        return new AdminProductResult.ProductRegisterV2(
                product.getId()
                , product.getName()
                , product.getDescription()
                , product.getPrice().setScale(0, RoundingMode.HALF_UP).intValue()
                , productInventory.getStock()
                , product.getCategory().getId()
        );
    }

    @Transactional
    public Void removeProduct(Long productId) {

        Product product = adminProductService.findById(productId);

        boolean exists = adminPurchaseService.existsByProductAndPurchaseStatus(product, "COMPLETED");

        if (exists) {
            throw new EcommerceException(EcommerceErrorCode.PRODUCT_ALREADY_PURCHASED);
        }

        product.removeProduct();

        adminProductService.updateProduct(product);

        return null;

    }
}
