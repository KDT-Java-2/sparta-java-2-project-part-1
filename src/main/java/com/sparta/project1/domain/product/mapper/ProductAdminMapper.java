package com.sparta.project1.domain.product.mapper;

import com.sparta.project1.domain.product.dto.admin.ProductAdminResponse;
import com.sparta.project1.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductAdminMapper {
    @Mapping(source = "id", target = "productId")
    ProductAdminResponse toResponse(Product product);
}
