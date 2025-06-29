package com.scb.project.commerce.domain.product.mapper;

import com.scb.project.commerce.domain.product.dto.ProductResponse;
import com.scb.project.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    /**
     * 상품 엔티티를 응답 DTO로 변환하는 매퍼
     *
     * <p>상품 정보를 API 응답 형태로 가공하여 반환합니다.</p>
     *
     * @param product 상품 엔티티
     * @return 상품 응답 DTO
     */
    ProductResponse toResponse(Product product);
}
