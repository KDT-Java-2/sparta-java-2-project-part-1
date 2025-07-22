package com.sparta.bootcamp.java_2_example.domain.product.mapper;

import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductResponse;
import com.sparta.bootcamp.java_2_example.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id",   target = "category.id")
    @Mapping(source = "category.name", target = "category.name")
    ProductResponse toResponse(Product product);

}
