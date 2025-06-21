package com.sparta.commerce_project_01.domain.category.repository;

import com.sparta.commerce_project_01.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
