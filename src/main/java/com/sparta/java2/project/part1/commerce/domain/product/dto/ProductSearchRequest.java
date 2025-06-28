package com.sparta.java2.project.part1.commerce.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest {
    /*
    - `category` (Long, 선택): 특정 카테고리 ID에 속한 상품만 필터링합니다.
            - `minPrice` (Integer, 선택): 최소 가격을 설정하여 해당 가격 이상의 상품만 필터링합니다.
            - `maxPrice` (Integer, 선택): 최대 가격을 설정하여 해당 가격 이하의 상품만 필터링합니다.
            - `page` (Integer, 선택, 기본값: 0): 조회할 페이지 번호 (0부터 시작).
            - `size` (Integer, 선택, 기본값: 10): 한 페이지에 보여줄 상품의 개수.
- `sortBy` (String, 선택, 예: `price,asc` 또는 `createdAt,desc`): 정렬 기준. `(필드명),(정렬방식)` 형식으로 요청합니다.
     */
    Long category;

    Integer minPrice;

    Integer maxPrice;

    @JsonProperty(defaultValue = "0")
    Integer page;

    @JsonProperty(defaultValue = "10")
    Integer size;

    String sortBy;
}
