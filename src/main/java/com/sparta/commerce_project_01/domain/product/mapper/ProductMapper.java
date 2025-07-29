package com.sparta.commerce_project_01.domain.product.mapper;

import com.sparta.commerce_project_01.domain.product.dto.ProductSearchResponse;
import com.sparta.commerce_project_01.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductSearchResponse toResponse(Product product);

  Product toEntity(ProductSearchResponse productSearchResponse);
}