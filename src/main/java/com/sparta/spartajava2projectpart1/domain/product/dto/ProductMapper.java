package com.sparta.spartajava2projectpart1.domain.product.dto;

import com.sparta.spartajava2projectpart1.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductSearchResponse toResponse(Product product);

    Product toEntity(ProductSearchResponse productSearchResponse);
}
