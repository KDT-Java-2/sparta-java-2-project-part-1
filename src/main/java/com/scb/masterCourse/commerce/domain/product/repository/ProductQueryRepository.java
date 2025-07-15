package com.scb.masterCourse.commerce.domain.product.repository;

import static com.scb.masterCourse.commerce.domain.brand.entity.QBrand.brand;
import static com.scb.masterCourse.commerce.domain.product.entity.QProduct.product;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scb.masterCourse.commerce.domain.product.dto.ProductQueryRequest;
import com.scb.masterCourse.commerce.domain.product.dto.ProductQueryResponse;
import com.scb.masterCourse.commerce.domain.product.dto.QProductQueryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<ProductQueryResponse> findPagedProducts(ProductQueryRequest request, Pageable pageable) {
        List<ProductQueryResponse> products = queryFactory
            .select(new QProductQueryResponse(
                product.id,
                product.name,
                brand.name,
                product.description,
                product.price,
                product.stock
            ))
            .from(product)
            .join(product.brand, brand)
            .where(
                categoryEq(request.getCategory()),
                priceGoe(request.getMinPrice()),
                priceLoe(request.getMaxPrice())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = queryFactory
            .select(product.count())
            .from(product)
            .where(
                categoryEq(request.getCategory()),
                priceGoe(request.getMinPrice()),
                priceLoe(request.getMaxPrice())
            )
            .fetchOne();

        if (total == null) {
            total = 0L;
        }

        return new PageImpl<>(products, pageable, total);
    }

    private BooleanExpression categoryEq(Long categoryId) {
        return categoryId != null ? product.category.id.eq(categoryId) : null;
    }

    private BooleanExpression priceGoe(Integer minPrice) {
        return minPrice != null ? product.price.goe(minPrice) : null;
    }

    private BooleanExpression priceLoe(Integer maxPrice) {
        return maxPrice != null ? product.price.loe(maxPrice) : null;
    }
}
