package com.sparta.bootcamp.java_2_example.domain.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductSearchRequest;
import com.sparta.bootcamp.java_2_example.domain.product.dto.ProductSearchResponse;
import com.sparta.bootcamp.java_2_example.domain.product.dto.QProductSearchResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.bootcamp.java_2_example.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<ProductSearchResponse> findAll(ProductSearchRequest request, Pageable pageable) {
        BooleanExpression[] conditions = {
                categoryEqual(request.getCategory()),
                minPriceGoe(request.getMinPrice()),
                maxPriceLoe(request.getMaxPrice())
        };

        List<ProductSearchResponse> contents = queryFactory
                .select(new QProductSearchResponse(
                        product.id,
                        product.name,
                        product.price,
                        product.stock
                ))
                .from(product)
                .where(conditions)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(product.count())
                .from(product)
                .where(conditions)
                .fetchOne();

        long totalCnt = count != null ? count : 0L;

        return new PageImpl<>(contents, pageable, totalCnt);
    }

    private BooleanExpression categoryEqual(Long category) {
        return ObjectUtils.isNotEmpty(category) ? product.category.id.eq(category) : null;
    }

    private BooleanExpression minPriceGoe(Integer minPrice) {
        return ObjectUtils.isNotEmpty(minPrice) ? product.price.goe(minPrice) : null;
    }

    private BooleanExpression maxPriceLoe(Integer maxPrice) {
        return ObjectUtils.isNotEmpty(maxPrice) ? product.price.loe(maxPrice) : null;
    }

}
