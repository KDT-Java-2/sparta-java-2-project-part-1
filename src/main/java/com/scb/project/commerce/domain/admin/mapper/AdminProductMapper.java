package com.scb.project.commerce.domain.admin.mapper;

import com.scb.project.commerce.domain.admin.dto.AdminProductCreateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminProductUpdateResponse;
import com.scb.project.commerce.domain.category.entity.Category;
import com.scb.project.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminProductMapper {

    /**
     * 관리자 상품 등록 요청을 상품 엔티티로 변환하는 매퍼
     *
     * @param request  상품 등록 요청 DTO
     * @param category 등록할 상품의 카테고리 엔티티
     * @return 생성된 상품 엔티티
     */
    @Mapping(source = "request.name", target = "productName")
    Product toEntity(AdminProductCreateRequest request, Category category);

    /**
     * 상품 수정 응답 DTO로 변환
     *
     * @param product  수정된 상품 엔티티
     * @param category 상품에 속한 카테고리 엔티티
     * @return 수정된 상품 수정 응답 DTO
     */
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    AdminProductUpdateResponse toResponse(Product product, Category category);
}
