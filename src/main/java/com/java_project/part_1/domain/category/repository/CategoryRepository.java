package com.java_project.part_1.domain.category.repository;

import com.java_project.part_1.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  boolean existsByParent_Id(Long parentId);

}
