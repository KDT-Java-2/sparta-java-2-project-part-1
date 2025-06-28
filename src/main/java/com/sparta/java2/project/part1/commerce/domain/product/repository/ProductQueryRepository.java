package com.sparta.java2.project.part1.commerce.domain.product.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java2.project.part1.commerce.common.util.QueryDslUtil;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductSearchRequest;
import com.sparta.java2.project.part1.commerce.domain.product.dto.ProductSearchResponse;
import com.sparta.java2.project.part1.commerce.domain.product.dto.QProductSearchResponse;
import com.sparta.java2.project.part1.commerce.domain.product.entity.Product;
import com.sparta.java2.project.part1.commerce.domain.product.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {
    private JPAQueryFactory jpaQueryFactory;

    public Page<ProductSearchResponse> search(ProductSearchRequest request, Pageable pageable) {
        List<ProductSearchResponse> data = jpaQueryFactory.query()
                .select(new QProductSearchResponse(
                        QProduct.product.id,
                        QProduct.product.name,
                        QProduct.product.price,
                        QProduct.product.stock
                ))
                .from(QProduct.product)
                .where(
                        equalsCategoryId(request.getCategory()),
                        betweenPrice(request.getMinPrice(), request.getMaxPrice())
                )
                .orderBy(toOrderSpecifiers(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory.query()
                .select(QProduct.product.count())
                .from(QProduct.product)
                .where(
                        equalsCategoryId(request.getCategory()),
                        betweenPrice(request.getMinPrice(), request.getMaxPrice())
                )
                .fetchOne();

        return new PageImpl<>(data, pageable, Objects.requireNonNull(total));
    }

    private BooleanExpression equalsCategoryId(Long categoryId) {
        return categoryId == null ? null : QProduct.product.category.id.eq(categoryId);
    }

    private BooleanBuilder betweenPrice(Integer minPrice, Integer maxPrice) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(minPrice == null ? null : QProduct.product.price.goe(minPrice));
        booleanBuilder.and(maxPrice == null ? null : QProduct.product.price.loe(maxPrice));
        return booleanBuilder;
    }

    private OrderSpecifier<?>[] toOrderSpecifiers(Sort sort) {
        return sort.stream()
                .map(order -> {
                    PathBuilder<Product> pathBuilder = new PathBuilder<>(Product.class, QProduct.product.getMetadata().getName());
                    Order direction = order.isAscending() ? Order.ASC : Order.DESC;
                    return new OrderSpecifier<>(direction, pathBuilder.get(order.getProperty(), Comparable.class));
                })
                .toArray(OrderSpecifier[]::new);
    }
}