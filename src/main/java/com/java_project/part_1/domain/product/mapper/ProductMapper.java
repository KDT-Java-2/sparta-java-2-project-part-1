package com.java_project.part_1.domain.product.mapper;

import com.java_project.part_1.domain.product.dto.ProductSearchResponse;
import com.java_project.part_1.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductSearchResponse toResponse(Product product);
}
