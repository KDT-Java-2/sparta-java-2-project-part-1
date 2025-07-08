package com.sparta.coupang_commerce.domain.product.mapper;

import com.sparta.coupang_commerce.domain.category.entity.Category;
import com.sparta.coupang_commerce.domain.product.dto.ProductCategoryResponse;
import com.sparta.coupang_commerce.domain.product.dto.ProductPageResponse;
import com.sparta.coupang_commerce.domain.product.dto.ProductResponse;
import com.sparta.coupang_commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductPageResponse toProductPageResponse(Product product);

  @Mapping(target = "id", source = "product.id")
  @Mapping(target = "name", source = "product.name")
  @Mapping(target = "price", source = "product.price")
  @Mapping(target = "category", source = "productCategoryResponse")
  ProductResponse toProductResponse(Product product, ProductCategoryResponse productCategoryResponse);
  
  ProductCategoryResponse toProductCategoryResponse(Category category);
}
