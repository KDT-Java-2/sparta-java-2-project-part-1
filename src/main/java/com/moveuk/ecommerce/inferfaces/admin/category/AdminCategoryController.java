package com.moveuk.ecommerce.inferfaces.admin.category;

import com.moveuk.ecommerce.application.admin.category.AdminCategoryFacade;
import com.moveuk.ecommerce.inferfaces.admin.category.request.AdminCategoryRequest;
import com.moveuk.ecommerce.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AdminCategoryController {

    private final AdminCategoryFacade adminCategoryFacade;

    @PostMapping("/api/admin/categories")
    public ApiResponse<Map> createCategory(@ModelAttribute AdminCategoryRequest.CategoryRegisterV1 categoryRegisterV1){
        return ApiResponse.success(adminCategoryFacade.createCategory(AdminCategoryRequest.CategoryRegisterV1.from(categoryRegisterV1)));
    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ApiResponse<Map> updateCategory(@PathVariable Long categoryId, @ModelAttribute AdminCategoryRequest.CategoryRegisterV1 categoryRegisterV1 ){
        return ApiResponse.success(adminCategoryFacade.updateCategory(AdminCategoryRequest.CategoryRegisterV1.from(categoryId, categoryRegisterV1)));
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ApiResponse<Map> removeCategory(@PathVariable Long categoryId){
        return ApiResponse.success(adminCategoryFacade.removeCategory(categoryId));
    }


}
