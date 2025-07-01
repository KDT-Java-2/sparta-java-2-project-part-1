package com.sparta.javamarket.domain.product.mapper;

import com.sparta.javamarket.domain.product.dto.ProductSearchResponse;
import com.sparta.javamarket.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductSearchResponse toResponse(Product product);
}
