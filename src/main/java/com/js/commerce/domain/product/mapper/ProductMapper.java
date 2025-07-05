package com.js.commerce.domain.product.mapper;

import com.js.commerce.domain.product.dto.ProductSearchResponse;
import com.js.commerce.domain.product.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  // 엔티티 → 단일 DTO
  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "price", target = "price")
  @Mapping(source = "stock", target = "stock")
  ProductSearchResponse toResponse(Product product);

  // 엔티티 리스트 → DTO 리스트 (MapStruct가 자동으로 구현)
  List<ProductSearchResponse> toResponseList(List<Product> products);

}
