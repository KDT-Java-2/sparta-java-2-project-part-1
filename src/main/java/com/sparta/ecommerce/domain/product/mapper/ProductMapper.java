package com.sparta.ecommerce.domain.product.mapper;

import com.sparta.ecommerce.domain.category.entity.Category;
import com.sparta.ecommerce.domain.product.dto.ProductCategoryResponse;
import com.sparta.ecommerce.domain.product.dto.ProductCreateRequest;
import com.sparta.ecommerce.domain.product.dto.ProductCreateResponse;
import com.sparta.ecommerce.domain.product.dto.ProductInfoResponse;
import com.sparta.ecommerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(source = "request.name", target = "productNm")
  @Mapping(source = "category", target = "category")
  Product toEntity(ProductCreateRequest request, Category category);

  @Mapping(source = "categoryNm", target = "name")
  ProductCategoryResponse toCategoryResponse(Category category);

  @Mapping(source = "productNm", target = "name")
  ProductInfoResponse toProductResponse(Product product);

  @Mapping(source = "id", target = "productId")
  ProductCreateResponse toProductCreateResponse(Product product);
}