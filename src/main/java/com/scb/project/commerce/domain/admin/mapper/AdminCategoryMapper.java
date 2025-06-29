package com.scb.project.commerce.domain.admin.mapper;

import com.scb.project.commerce.domain.admin.dto.AdminCategoryCreateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminCategoryResponse;
import com.scb.project.commerce.domain.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminCategoryMapper {

    /**
     * 카테고리 등록 요청 정보를 Category 엔티티로 변환
     *
     * @param request  카테고리 등록 요청 DTO
     * @param category 상위 카테고리 엔티티 (없을 경우 null)
     * @return Category 엔티티 객체
     */
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "parent", source = "category")
    Category toEntity(AdminCategoryCreateRequest request, Category category);

    /**
     * Category 엔티티를 관리자 카테고리 응답 DTO로 변환
     *
     * @param category 카테고리 엔티티
     * @return AdminCategoryResponse DTO
     */
    @Mapping(target = "parentId", source = "parent.id")
    AdminCategoryResponse toResponse(Category category);
}
