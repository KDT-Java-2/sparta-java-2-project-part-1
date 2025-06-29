package com.scb.project.commerce.domain.admin.mapper;

import com.scb.project.commerce.domain.admin.dto.AdminProductCreateRequest;
import com.scb.project.commerce.domain.category.entity.Category;
import com.scb.project.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminProductMapper {

    /**
     * 관리자 상품 등록 요청을 상품 엔티티로 변환하는 매퍼
     *
     * <p>요청 정보와 카테고리 엔티티를 기반으로 Product 엔티티를 생성합니다.</p>
     *
     * @param request  상품 등록 요청 DTO
     * @param category 등록할 상품의 카테고리 엔티티
     * @return 생성된 상품 엔티티
     */
    @Mapping(source = "request.name", target = "productName")
    Product toEntity(AdminProductCreateRequest request, Category category);
}
