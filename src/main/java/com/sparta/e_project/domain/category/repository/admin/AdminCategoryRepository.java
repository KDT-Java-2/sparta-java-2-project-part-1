package com.sparta.e_project.domain.category.repository.admin;

import com.sparta.e_project.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCategoryRepository extends JpaRepository<Category, Long> {

  boolean existsByName(String name);

  boolean existsByParent(Category parent);
}
