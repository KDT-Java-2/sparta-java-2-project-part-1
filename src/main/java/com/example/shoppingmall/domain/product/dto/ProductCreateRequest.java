package com.example.shoppingmall.domain.product.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {
    String name;
    String description;
    BigDecimal price;
    Integer stock;
    Long categoryId;
    List<OptionGroupRequest> optionGroups;
    List<VariantRequest> variants;
    List<ImageRequest> images;

    @Getter
    @NoArgsConstructor
    public static class OptionGroupRequest {
        String name;
        List<String> values;
    }

    @Getter
    @NoArgsConstructor
    public static class VariantRequest {
        BigDecimal price;
        Integer stock;
        List<Integer> optionValueIndexes;
    }

    @Getter
    @NoArgsConstructor
    public static class ImageRequest {
        String url;
        String type;
        Integer sortOrder;
    }
} 