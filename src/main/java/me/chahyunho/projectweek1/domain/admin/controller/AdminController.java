package me.chahyunho.projectweek1.domain.admin.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.common.enums.response.ApiResponse;
import me.chahyunho.projectweek1.domain.admin.dto.AdminCategoryRequest;
import me.chahyunho.projectweek1.domain.admin.dto.AdminCategoryResponse;
import me.chahyunho.projectweek1.domain.admin.dto.AdminCategoryUpdateResponse;
import me.chahyunho.projectweek1.domain.admin.dto.AdminProductRequest;
import me.chahyunho.projectweek1.domain.admin.dto.AdminProductResponse;
import me.chahyunho.projectweek1.domain.admin.dto.AdminProductUpdateResponse;
import me.chahyunho.projectweek1.domain.admin.service.AdminService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

  private final AdminService adminService;

  // 상품 등록
  @PostMapping("/products")
  public ApiResponse<AdminProductResponse> createAdminProduct(
      @Valid @RequestBody AdminProductRequest request) {
    return ApiResponse.success(adminService.createAdminProduct(request));
  }

  // 상품 수정
  @PutMapping("/products/{productId}")
  public ApiResponse<AdminProductUpdateResponse> updateAdminProduct(@PathVariable Long productId,
      @Valid @RequestBody AdminProductRequest request) {
    return ApiResponse.success(adminService.updateAdminProduct(productId, request));
  }

  // 상품 삭제
  @DeleteMapping("/products/{productId}")
  public ApiResponse<AdminProductResponse> deleteAdminProduct(@PathVariable Long productId) {
    adminService.deleteAdminProduct(productId);
    return ApiResponse.success();
  }

  // 카테고리 등록
  @PostMapping("/categories")
  public ApiResponse<AdminCategoryResponse> createAdminCategory(
      @Valid @RequestBody AdminCategoryRequest request) {
    return ApiResponse.success(adminService.createAdminCategory(request));
  }

  // 카테고리 수정
  @PutMapping("/categories/{categoryId}")
  public ApiResponse<AdminCategoryUpdateResponse> updateAdminCategory(@PathVariable Long categoryId,
      @Valid @RequestBody AdminCategoryRequest request) {
    return ApiResponse.success(adminService.updateAdminCategory(categoryId, request));
  }

  // 카테고리 삭제
  @DeleteMapping("/categories/{categoryId}")
  public ApiResponse<Valid> deleteAdminCategory(@PathVariable Long categoryId) {
    adminService.deleteAdminCategory(categoryId);
    return ApiResponse.success();
  }
}
