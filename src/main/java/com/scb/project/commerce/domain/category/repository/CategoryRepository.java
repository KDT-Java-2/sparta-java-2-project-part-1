package com.scb.project.commerce.domain.category.repository;

import com.scb.project.commerce.domain.category.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * 카테고리명으로 카테고리 찾는 메서드
     *
     * @param name 카테고리명
     * @return 카테고리 객체
     */
    Category findByName(String name);

    /**
     * 부모 카테고리 객체로 자식 카테고리 찾는 메서드
     *
     * @param parent 부모 카테고리 객체
     * @return 자식 카테고리 객체 리스트
     */
    List<Category> findAllByParent(Category parent);

    /**
     * 부모 카테고리 하위의 자식 카테고리 삭제하는 메서드
     *
     * @param parent 부모 카테고리 객체
     */
    void deleteAllByParent(Category parent);

    /**
     * 부모 카테고리 하위의 자식 카테고리가 존재하는지 확인하는 메서드
     *
     * @param parent 부모 카테고리 객체
     * @return boolean (true / false)
     */
    boolean existsByParent(Category parent);
}
