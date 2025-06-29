package com.scb.project.commerce.domain.category.repository;

import static com.scb.project.commerce.domain.category.entity.QCategory.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scb.project.commerce.domain.category.entity.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * 카테고리 계층 구조 조회 쿼리
     *
     * <p>QueryDSL을 사용하여 모든 카테고리를 parent와 함께 fetch join으로 조회합니다.</p>
     * <p>이 쿼리는 트리 구조 구성을 위한 사전 데이터를 제공합니다.</p>
     *
     * @return 모든 카테고리 엔티티 리스트 (부모 포함)
     */
    public List<Category> findCategoryHierarchy() {
        return queryFactory
            .selectFrom(category)
            .leftJoin(category.parent)
            .fetchJoin()
            .fetch();
    }
}
