package com.moveuk.ecommerce.inferfaces.admin.product;

import com.moveuk.ecommerce.application.admin.product.AdminProductFacade;
import com.moveuk.ecommerce.application.admin.product.response.AdminProductResult;
import com.moveuk.ecommerce.inferfaces.admin.product.reqest.AdminProductRequest;
import com.moveuk.ecommerce.inferfaces.admin.product.response.AdminProductResponse;
import com.moveuk.ecommerce.support.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductFacade adminProductFacade;

    @PostMapping("/api/admin/products")
    public ApiResponse<AdminProductResponse.ProductResponseV1> createProduct(@RequestBody @Valid AdminProductRequest.ProductRegisterV1 productRegisterV1){
        AdminProductResult.ProductRegisterV1 response = adminProductFacade.createProduct(AdminProductRequest.ProductRegisterV1.from(productRegisterV1));
        return ApiResponse.success(AdminProductResponse.ProductResponseV1.of(response));
    }

    @PutMapping("/api/admin/products/{productId}")
    public ApiResponse<AdminProductResponse.ProductResponseV2> updateProduct(@PathVariable @Valid  Long productId, @RequestBody @Valid AdminProductRequest.ProductRegisterV1 productRegisterV2){
        AdminProductResult.ProductRegisterV2 response = adminProductFacade.updateProduct(AdminProductRequest.ProductRegisterV1.from(productId, productRegisterV2));
        return ApiResponse.success(AdminProductResponse.ProductResponseV2.of(response));
    }

    @DeleteMapping("/api/admin/products/{productId}")
    public ApiResponse<Void> removeProduct(@PathVariable Long productId){
        return ApiResponse.success(adminProductFacade.removeProduct(productId));
    }
}
