package com.socialcommerce.domain.product.mapper;

import com.socialcommerce.domain.product.dto.ProductResponse;
import com.socialcommerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "categoryId", source = "category.id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  ProductResponse toResponse(Product product);
}
