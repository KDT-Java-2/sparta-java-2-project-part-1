package com.sparta.spartajava2projectpart1.domain.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartajava2projectpart1.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.spartajava2projectpart1.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<Product> findProducts(Long categoryId, Integer minPrice, Integer maxPrice, Pageable pageable) {
        List<Product> products = queryFactory.select(product)
                .from(product)
                .where(
                        priceBetween(minPrice, maxPrice),
                        product.category.id.eq(categoryId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.createdAt.desc())
                .fetch();

        Long totalCount = queryFactory.select(product.count())
                .from(product)
                .where(
                        priceBetween(minPrice, maxPrice),
                        product.category.id.eq(categoryId)
                )
                .fetchOne();

        return new PageImpl<>(products, pageable, totalCount);
    }

    private BooleanExpression priceBetween(Integer minPrice, Integer maxPrice) {
        return product.price.between(minPrice, maxPrice);
    }
}
