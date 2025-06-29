package com.sparta.spartajava2projectpart1.domain.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartajava2projectpart1.domain.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.spartajava2projectpart1.domain.category.entity.QCategory.category;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Category> findCategoryHierarchy() {
        return queryFactory.selectFrom(category)
                .join(category.parent)
                .fetchJoin()
                .fetch();
    }
}
