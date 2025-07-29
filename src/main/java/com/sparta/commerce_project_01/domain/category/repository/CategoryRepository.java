package com.sparta.commerce_project_01.domain.category.repository;

import com.sparta.commerce_project_01.domain.category.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


  // 모든 자식 카테고리 찾기
  List<Category> findByParentId(Long parent_id);

  // 특정 부모의 자식 카테고리들을 찾으려면
  List<Category> findByParent(Category parent);

  // 루트 카테고리들을 찾으려면 (부모가 없는 카테고리)
  List<Category> findByParentIsNull();


  Category getCategoryById(Long id);
}
