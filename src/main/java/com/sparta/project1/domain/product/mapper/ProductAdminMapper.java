package com.sparta.project1.domain.product.mapper;

import com.sparta.project1.domain.product.dto.admin.ProductAdminResponse;
import com.sparta.project1.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAdminMapper {
    ProductAdminResponse toResponse(Product product);
}
