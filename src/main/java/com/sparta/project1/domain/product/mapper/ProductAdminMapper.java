package com.sparta.project1.domain.product.mapper;

import com.sparta.project1.domain.product.dto.ProductDetailResponse;
import com.sparta.project1.domain.product.dto.admin.ProductAdminRegisterResponse;
import com.sparta.project1.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAdminMapper {
    ProductAdminRegisterResponse toResponse(Product product);
}
