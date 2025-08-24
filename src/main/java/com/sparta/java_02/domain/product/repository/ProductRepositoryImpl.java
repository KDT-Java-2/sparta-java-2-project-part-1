package com.sparta.java_02.domain.product.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java_02.domain.category.dto.CategoryStatsDTO;
import com.sparta.java_02.domain.product.dto.CategoryProductDTO;
import com.sparta.java_02.domain.product.dto.ProductSearchCondition;
import com.sparta.java_02.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sparta.java_02.domain.category.entity.QCategory.category;
import static com.sparta.java_02.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> searchProducts(ProductSearchCondition condition) {
        return queryFactory
                .selectFrom(product)
                .join(product.category, category) // 카테고리 이름 검색을 위해 조인
                .where(
                        productNameEq(condition.getProductName()),
                        categoryNameEq(condition.getCategoryName()),
                        priceGoe(condition.getMinPrice()),
                        priceLoe(condition.getMaxPrice())
                )
                .fetch();
    }

    //------------------------- BooleanExpression을 활용한 동적 조건 메서드 -------------------------
    private BooleanExpression productNameEq(String productName) {
        return StringUtils.hasText(productName) ? product.name.eq(productName) : null;
    }

    private BooleanExpression categoryNameEq(String categoryName) {
        return StringUtils.hasText(categoryName) ? category.name.eq(categoryName) : null;
    }

    private BooleanExpression priceGoe(Integer minPrice) {
        return minPrice != null ? product.price.goe(minPrice) : null;
    }

    private BooleanExpression priceLoe(Integer maxPrice) {
        return maxPrice != null ? product.price.loe(maxPrice) : null;
    }

    @Override
    public List<CategoryProductDTO> findProductsByCategory() {
        return queryFactory
                .select(Projections.constructor(CategoryProductDTO.class,
                        category.name,
                        product.name,
                        product.price.doubleValue(),
                        product.stock
                ))
                .from(product)
                .join(product.category, category)
                .fetch();
    }

    @Override
    public List<CategoryStatsDTO> findCategoryStats() {
        return queryFactory
                .select(Projections.constructor(CategoryStatsDTO.class,
                        category.name,
                        product.count(),
                        product.stock.sum().as("totalStock") // sum() 함수 사용
                ))
                .from(product)
                .join(product.category, category)
                .groupBy(category.name) // category.name으로 그룹화
                .orderBy(category.name.asc()) // 정렬
                .fetch();
    }
}