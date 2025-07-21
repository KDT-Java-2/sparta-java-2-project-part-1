package com.scb.masterCourse.commerce.domain.admin.mapper;

import com.scb.masterCourse.commerce.domain.admin.dto.AdminProductResponse;
import com.scb.masterCourse.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminProductMapper {

    @Mapping(target = "productId", source = "id")
    @Mapping(target = "productName", source = "name")
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "categoryName", source = "category.name")
    AdminProductResponse toResponse(Product product);

}
