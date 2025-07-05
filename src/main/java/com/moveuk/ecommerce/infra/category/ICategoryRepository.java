package com.moveuk.ecommerce.infra.category;

import com.moveuk.ecommerce.domain.category.Category;
import com.moveuk.ecommerce.domain.category.CategoryRepository;
import com.moveuk.ecommerce.domain.category.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ICategoryRepository implements CategoryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Category> findAllWithParent() {
        QCategory category = QCategory.category;

        return queryFactory
                .selectFrom(category)
                .leftJoin(category.parent).fetchJoin()
                .fetch();
    }
}
