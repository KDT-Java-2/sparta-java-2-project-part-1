package com.moveuk.ecommerce.inferfaces.product;

import com.moveuk.ecommerce.application.product.ProductFacade;
import com.moveuk.ecommerce.application.product.response.ProductResult;
import com.moveuk.ecommerce.inferfaces.product.request.ProductRequest;
import com.moveuk.ecommerce.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductFacade productFacade;

    @GetMapping("/api/products")
    public ApiResponse<Page<ProductResult.ProductRegisterV1>> findByProductsAll(@ModelAttribute ProductRequest.ProductRegisterV1 productRegisterV1){
        return ApiResponse.success(productFacade.listProducts(ProductRequest.ProductRegisterV1.from(productRegisterV1)));
    }

    @GetMapping("/api/products/{productId}")
    public ApiResponse<ProductResult.ProductRegisterV1> findByProductsId(@PathVariable long productId){
        return ApiResponse.success(productFacade.findByProductsId(productId));
    }
}
