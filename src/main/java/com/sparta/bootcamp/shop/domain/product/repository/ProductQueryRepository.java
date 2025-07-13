package com.sparta.bootcamp.shop.domain.product.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.bootcamp.shop.domain.category.dto.QCategoryResponse;
import com.sparta.bootcamp.shop.domain.category.entity.QCategory;
import com.sparta.bootcamp.shop.domain.product.dto.ProductResponse;
import com.sparta.bootcamp.shop.domain.product.dto.QProductResponse;
import com.sparta.bootcamp.shop.domain.product.entity.Product;
import com.sparta.bootcamp.shop.domain.product.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<ProductResponse> findProducts(Long categoryId,
                                              Integer minPrice,
                                              Integer maxPrice,
                                              Pageable pageable) {

        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        BooleanBuilder condition = new BooleanBuilder();

        if (categoryId != null) {
            condition.and(product.category.id.eq(categoryId));
        }
        if (minPrice != null) {
            condition.and(product.price.goe(minPrice));
        }
        if (maxPrice != null) {
            condition.and(product.price.loe(maxPrice));
        }

        List<OrderSpecifier<?>> orderSpecifiers = getSortRuleList(pageable.getSort(), product);

        List<ProductResponse> content = queryFactory
                .select(new QProductResponse(
                        product.id,
                        new QCategoryResponse(
                                product.category.id,
                                product.category.name
                        ),
                        product.name,
                        product.description,
                        product.price,
                        product.stock,
                        product.createdAt
                ))
                .from(product)
                .where(condition)
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long result = queryFactory
                .select(product.count())
                .from(product)
                .where(condition)
                .fetchOne();
        long total = result != null ? result : 0L;

        return new PageImpl<>(content, pageable, total);
    }

    private List<OrderSpecifier<?>> getSortRuleList(Sort sort, QProduct product) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        sort.forEach(order -> {
            PathBuilder<Product> pathBuilder = new PathBuilder<>(Product.class, "product");
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            orderSpecifiers.add(new OrderSpecifier<>(direction, pathBuilder.getComparable(order.getProperty(), Comparable.class)));
        });

        return orderSpecifiers;
    }

//    private BooleanExpression emailContains(String email) {
//        return StringUtils.hasText(email) ? user.email.contains(email) : null;
//    }
}
