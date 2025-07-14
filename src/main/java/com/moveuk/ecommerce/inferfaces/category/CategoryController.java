package com.moveuk.ecommerce.inferfaces.category;

import com.moveuk.ecommerce.application.category.CategoryFacade;
import com.moveuk.ecommerce.inferfaces.category.response.CategoryTreeResponse;
import com.moveuk.ecommerce.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFacade categoryFacade;

    @GetMapping("/api/categories/hierarchy")
    public ApiResponse<List<CategoryTreeResponse>> findAllCategory(){
        return ApiResponse.success(CategoryTreeResponse.from(categoryFacade.findAllCategory()));
    }
}
