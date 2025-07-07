package com.moveuk.ecommerce.inferfaces.admin.category;

import com.moveuk.ecommerce.application.admin.category.AdminCategoryFacade;
import com.moveuk.ecommerce.application.admin.category.response.AdminCategoryResult;
import com.moveuk.ecommerce.inferfaces.admin.category.request.AdminCategoryRequest;
import com.moveuk.ecommerce.inferfaces.admin.category.response.AdminCategoryResponse;
import com.moveuk.ecommerce.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AdminCategoryController {

    private final AdminCategoryFacade adminCategoryFacade;

    @PostMapping("/api/admin/categories")
    public ApiResponse<AdminCategoryResponse.CategoryResponseV1> createCategory(@ModelAttribute AdminCategoryRequest.CategoryRegisterV1 categoryRegisterV1){
        AdminCategoryResult.CategoryRegisterV1 response =  adminCategoryFacade.createCategory(AdminCategoryRequest.CategoryRegisterV1.from(categoryRegisterV1));
        return ApiResponse.success(AdminCategoryResponse.CategoryResponseV1.of(response));
    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ApiResponse<List<AdminCategoryResponse.AdminCategoryTreeResponse>> updateCategory(@PathVariable Long categoryId, @ModelAttribute AdminCategoryRequest.CategoryRegisterV1 categoryRegisterV1 ){
        List<AdminCategoryResult.CategoryTreeDto>response = adminCategoryFacade.updateCategory(AdminCategoryRequest.CategoryRegisterV1.from(categoryId, categoryRegisterV1));
        return ApiResponse.success(AdminCategoryResponse.AdminCategoryTreeResponse.of(response));
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ApiResponse<Void> removeCategory(@PathVariable Long categoryId){
        return ApiResponse.success(adminCategoryFacade.(categoryId));
    }


}
