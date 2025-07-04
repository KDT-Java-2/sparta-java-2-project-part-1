package com.example.shoppingmall.domain.product.mapper;

import com.example.shoppingmall.domain.product.dto.ProductOptionDto;
import com.example.shoppingmall.domain.product.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.shoppingmall.domain.product.dto.ProductCreateRequest;
import com.example.shoppingmall.domain.product.dto.ProductDetailResponse;
import com.example.shoppingmall.domain.product.entity.Product;
import com.example.shoppingmall.domain.product.entity.ProductImage;
import com.example.shoppingmall.domain.product.dto.ProductCreateResponse;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "variants", qualifiedByName = "getTotalStock")
    @Mapping(target = "imageUrls", source = "images", qualifiedByName = "getImageUrls")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "variants", source = "variants", qualifiedByName = "toOptionDtoList")
    ProductDetailResponse toDetailResponse(Product product);
    
    @Named("getTotalStock")
    default Integer getTotalStock(List<ProductVariant> variants) {
        if (variants == null || variants.isEmpty()) {
            return 0;
        }
        return variants.stream()
                .mapToInt(variant -> variant.getStock())
                .sum();
    }
    
    @Named("getImageUrls")
    default List<String> getImageUrls(List<ProductImage> images) {
        if (images == null || images.isEmpty()) {
            return List.of();
        }
        return images.stream()
                .map(ProductImage::getUrl)
                .collect(Collectors.toList());
    }
    
    @Named("toOptionDtoList")
    default List<ProductOptionDto> toOptionDtoList(List<ProductVariant> variants) {
        if (variants == null || variants.isEmpty()) {
            return List.of();
        }
        return variants.stream()
                .map(variant -> ProductOptionDto.builder()
                        .id(variant.getId())
                        .optionSummary(
                            variant.getVariantOptions().stream()
                                .map(vo -> vo.getOptionValue().getOptionGroup().getName() + ":" + vo.getOptionValue().getValue())
                                .collect(Collectors.joining(", "))
                        )
                        .price(variant.getPrice())
                        .stock(variant.getStock())
                        .build())
                .collect(Collectors.toList());
    }

    Product fromCreateRequest(ProductCreateRequest request);

    default ProductCreateResponse toCreateResponse(Product product) {
        return new ProductCreateResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            getTotalStock(product.getVariants()),
            product.getCategory() != null ? product.getCategory().getId() : null,
            product.getCategory() != null ? product.getCategory().getName() : null,
            toOptionDtoList(product.getVariants()),
            getImageUrls(product.getImages())
        );
    }
}
