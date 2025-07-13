package com.sparta.commerce.domain.product.dto;

public record ProductSearchRequest(Integer minPrice, Integer maxPrice, Long categoryId) {

}
