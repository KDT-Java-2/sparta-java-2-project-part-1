package com.sparta.java_02.domain.product.mapper;

import com.sparta.java_02.domain.product.dto.ProductCreateRequestDto;
import com.sparta.java_02.domain.product.dto.ProductResponseDto;
import com.sparta.java_02.domain.product.dto.ProductUpdateRequestDto;
import com.sparta.java_02.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

  // 정적 인스턴스 (Spring Bean을 사용하지 않을 때)
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  // DTO -> Entity 변환 메서드
  @Mapping(target = "category", ignore = true)
  Product toEntity(ProductCreateRequestDto dto);

  // Entity -> Response DTO 변환 메서드
  // Product 엔티티의 category.name 필드를 ProductResponseDto의 categoryName 필드로 매핑
  @Mapping(source = "category.name", target = "categoryName")
  ProductResponseDto toResponseDto(Product entity);

  // Entity 업데이트 메서드
  void updateEntityFromDto(ProductUpdateRequestDto dto, @MappingTarget Product entity);
}