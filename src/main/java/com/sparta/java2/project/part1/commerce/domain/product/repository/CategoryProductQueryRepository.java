package com.sparta.java2.project.part1.commerce.domain.product.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java2.project.part1.commerce.domain.category.entity.QCategory;
import com.sparta.java2.project.part1.commerce.domain.product.dto.CategoryProductDto;
import com.sparta.java2.project.part1.commerce.domain.product.dto.QCategoryProductDto;
import com.sparta.java2.project.part1.commerce.domain.product.entity.QProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryProductQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<CategoryProductDto> findCategoryProductByName(String categoryName, Pageable pageable) {
        BooleanExpression condition1 = QCategory.category.name.eq(categoryName);
        return new PageImpl<>(
                findWithJoinOn(condition1),
                pageable,
                getTotalCountWithJoinOn(condition1));
    }

    public Page<CategoryProductDto> findCategoryProductById(Long categoryId, Pageable pageable) {
        BooleanExpression condition1 = QCategory.category.id.eq(categoryId);
        return new PageImpl<>(
                findWithJoinOn(condition1),
                pageable,
                getTotalCountWithJoinOn(condition1));
    }

    private List<CategoryProductDto> findWithJoinOn(Predicate... onConditions) {
        return queryFactory
                .select(new QCategoryProductDto(
                        QCategory.category.name,
                        QProduct.product.name,
                        QProduct.product.price,
                        QProduct.product.stock
                ))
                .from(QCategory.category)
                .innerJoin(QProduct.product.category, QCategory.category)
                .on(onConditions)
                .fetch();
    }

    private Long getTotalCountWithJoinOn(Predicate... onConditions) {
        return queryFactory
                .select(QProduct.product.count())
                .from(QCategory.category)
                .innerJoin(QProduct.product.category, QCategory.category)
                .on(onConditions)
                .fetchOne();
    }
}
