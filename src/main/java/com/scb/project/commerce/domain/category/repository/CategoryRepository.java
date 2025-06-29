package com.scb.project.commerce.domain.category.repository;

import com.scb.project.commerce.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * 자식이 있는지 여부를 확인합니다.
     *
     * @param category 확인할 부모 카테고리명
     * @return 자식 카테고리가 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByParent(Category category);
}
