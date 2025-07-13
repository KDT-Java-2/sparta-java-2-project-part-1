package com.sparta.commerce.domain.product.mapper;

import com.sparta.commerce.domain.product.dto.ProductDetailResponse;
import com.sparta.commerce.domain.product.dto.admin.ProductResponse;
import com.sparta.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductResponse toResponse(Product product);

  ProductDetailResponse toDetailResponse(Product product);

}
