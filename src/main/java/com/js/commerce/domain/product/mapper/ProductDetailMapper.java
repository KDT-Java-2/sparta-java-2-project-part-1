package com.js.commerce.domain.product.mapper;

import com.js.commerce.domain.category.entity.Category;
import com.js.commerce.domain.product.dto.CategoryDto;
import com.js.commerce.domain.product.dto.ProductDetailResponse;
import com.js.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {

  CategoryDto toDto(Category category);

  @Mapping(source = "category", target = "category")
  ProductDetailResponse toResponse(Product product);

}
