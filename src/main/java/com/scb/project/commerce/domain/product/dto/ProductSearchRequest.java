package com.scb.project.commerce.domain.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchRequest {

    // 카테고리
    Long category;

    // 최소 금액
    Integer minPrice;

    // 최대 금액
    Integer maxPrice;

    // 페이지 (default : 0)
    Integer page;

    // 사이즈 (default : 10)
    Integer size;

    // 정렬 기준
    String sortBy;

    /**
     * 페이지 번호와 사이즈를 기반으로 Pageable 객체 생성
     * <p>
     * 기본 페이지: 0
     * <p>
     * 기본 사이즈: 10
     *
     * @return 페이징 처리를 위한 Pageable 객체
     */
    public Pageable toPageable() {
        int page = this.page != null ? this.page : 0;
        int size = this.size != null ? this.size : 10;

        return PageRequest.of(page, size);
    }
}
