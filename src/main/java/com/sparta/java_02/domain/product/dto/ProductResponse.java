package com.sparta.java_02.domain.product.dto;

import com.sparta.java_02.domain.category.dto.CategoryResponse;
import com.sparta.java_02.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    private CategoryResponse category;

    public static ProductResponse from(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getCategory() != null ? CategoryResponse.from(product.getCategory()) : null
        );
    }
}

// 간단한 상품 응답 (카테고리 정보 제외)
@Getter
@NoArgsConstructor
@AllArgsConstructor
class ProductSimpleResponse {
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;

    public static ProductSimpleResponse from(Product product) {
        return new ProductSimpleResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStock()
        );
    }
} 