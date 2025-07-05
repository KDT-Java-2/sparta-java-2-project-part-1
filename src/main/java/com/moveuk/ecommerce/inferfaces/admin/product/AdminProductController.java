package com.moveuk.ecommerce.inferfaces.admin.product;

import com.moveuk.ecommerce.application.admin.product.AdminProductFacade;
import com.moveuk.ecommerce.inferfaces.admin.product.reqest.AdminProductRequest;
import com.moveuk.ecommerce.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductFacade adminProductFacade;

    @PostMapping("/api/admin/products")
    public ApiResponse<Map> createProduct(@ModelAttribute AdminProductRequest.ProductRegisterV1 productRegisterV1){
        return ApiResponse.success(adminProductFacade.createProduct(AdminProductRequest.ProductRegisterV1.from(productRegisterV1)));
    }

    @PutMapping("/api/admin/products/{productId}")
    public ApiResponse<Map> updateProduct(@PathVariable Long productId, @ModelAttribute AdminProductRequest.ProductRegisterV1 productRegisterV2){
        return ApiResponse.success(adminProductFacade.updateProduct(AdminProductRequest.ProductRegisterV1.from(productId, productRegisterV2)));
    }

    @DeleteMapping("/api/admin/products/{productId}")
    public ApiResponse<Map> removeProduct(@PathVariable Long productId){
        return ApiResponse.success(adminProductFacade.removeProduct(productId));
    }
}
