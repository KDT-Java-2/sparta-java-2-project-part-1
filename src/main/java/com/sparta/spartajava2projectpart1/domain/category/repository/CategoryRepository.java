package com.sparta.spartajava2projectpart1.domain.category.repository;

import com.sparta.spartajava2projectpart1.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
