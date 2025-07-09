package com.sparta.project1.domain.category.repository;

import com.sparta.project1.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
