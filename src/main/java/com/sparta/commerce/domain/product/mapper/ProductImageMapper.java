package com.sparta.commerce.domain.product.mapper;

import com.sparta.commerce.domain.product.dto.image.ProductImageResponse;
import com.sparta.commerce.domain.product.entity.ProductImage;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
  @Mapping(target = "productId", source = "product.id")
  @Mapping(target = "isThumbnail", source = "thumbnail")
  ProductImageResponse toResponse(ProductImage entity);
  List<ProductImageResponse> toResponses(List<ProductImage> entities);
}
