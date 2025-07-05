package com.js.commerce.domain.product.mapper;

import com.js.commerce.domain.category.entity.Category;
import com.js.commerce.domain.product.dto.admin.AdminProductCreateRequest;
import com.js.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    imports = java.math.BigDecimal.class
)
public interface ProductMapper {

  @Mapping(source = "request.name", target = "name")
  @Mapping(source = "request.description", target = "description")
  @Mapping(source = "request.stock", target = "stock")
  @Mapping(source = "category", target = "category")
  @Mapping(target = "price",
      expression = "java(BigDecimal.valueOf(request.getPrice()))")
  Product toEntity(AdminProductCreateRequest request, Category category);
}
