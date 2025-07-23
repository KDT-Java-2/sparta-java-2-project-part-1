package com.sparta.bootcamp.java_2_example.domain.product.mapper;

import com.sparta.bootcamp.java_2_example.domain.category.entity.Category;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductCreateRequest;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductCreateResponse;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductResponse;
import com.sparta.bootcamp.java_2_example.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id",   target = "category.id")
    @Mapping(source = "category.name", target = "category.name")
    ProductResponse toResponse(Product product);

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "toCategory")
    @Mapping(source = "price", target = "price", qualifiedByName = "toBigDecimal")
    Product toEntity(ProductCreateRequest request);

    ProductCreateResponse toCreateResponse(Product product);

    @Named("toCategory")
    default Category toCategory(Long productId) {
        return Category.builder()
                .id(productId)
                .build();
    }

    @Named("toBigDecimal")
    default BigDecimal toBigDecimal(Integer price) {
        return BigDecimal.valueOf(price);
    }
}
