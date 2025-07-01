package com.java_project.part_1.domain.category.repository;

import static com.java_project.part_1.domain.category.entity.QCategory.category;

import com.java_project.part_1.domain.category.entity.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepository {

  private final JPAQueryFactory queryFactory;

  public List<Category> findCategoryHierarchy() {
    return queryFactory.selectFrom(category)
        .leftJoin(category.parent)
        .fetchJoin()
        .fetch();
  }
}
