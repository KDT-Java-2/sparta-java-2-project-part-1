package com.scb.project.commerce.domain.product.mapper;

import com.scb.project.commerce.domain.product.dto.ProductResponse;
import com.scb.project.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    /**
     * Product 엔티티를 상품 상세 응답 DTO로 변환합니다.
     *
     * @param product 조회된 상품 엔티티
     * @return 상품 상세 정보 응답 DTO
     */
    ProductResponse toResponse(Product product);
}
