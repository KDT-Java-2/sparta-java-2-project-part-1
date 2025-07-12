package com.sparta.commerce.domain.product.mapper;

import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.product.dto.CategoryResponse;
import com.sparta.commerce.domain.product.dto.ProductDetailResponse;
import com.sparta.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(source = "category" , target = "category")
  ProductDetailResponse toProductDetailResponse(Product product);
}
