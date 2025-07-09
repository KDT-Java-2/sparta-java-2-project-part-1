package com.sparta.project1.domain.product.mapper;

import com.sparta.project1.domain.product.dto.ProductDetailResponse;
import com.sparta.project1.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDetailResponse toResponse(Product product);
}
