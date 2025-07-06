package com.sparta.javamarket.domain.admin.mapper;

import com.sparta.javamarket.domain.admin.dto.AdminCategoryCreateRequest;
import com.sparta.javamarket.domain.admin.dto.AdminCategoryCreateResponse;
import com.sparta.javamarket.domain.admin.dto.AdminCreateRequest;
import com.sparta.javamarket.domain.admin.dto.AdminCreateResponse;
import com.sparta.javamarket.domain.category.entity.Category;
import com.sparta.javamarket.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {

  @Mapping(source = "id", target = "productId")
  AdminCreateResponse toAdminCreateResponse(Product product);

  @Mapping(source = "id", target = "id")
  AdminCategoryCreateResponse toCategoryAdminResponse(Category category);
}
