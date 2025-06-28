package com.sparta.java_02.domain.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {
    
    @NotBlank(message = "상품명은 필수입니다.")
    private String name;
    
    @NotBlank(message = "상품 설명은 필수입니다.")
    private String description;
    
    @NotNull(message = "가격은 필수입니다.")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private Integer price;
    
    @NotNull(message = "재고는 필수입니다.")
    @Min(value = 0, message = "재고는 0 이상이어야 합니다.")
    private Integer stock;
    
    @NotNull(message = "카테고리 ID는 필수입니다.")
    private Long categoryId;

    public ProductCreateRequest(String name, String description, Integer price, Integer stock, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }
} 