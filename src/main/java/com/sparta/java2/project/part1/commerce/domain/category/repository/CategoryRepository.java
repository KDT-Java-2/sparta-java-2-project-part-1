package com.sparta.java2.project.part1.commerce.domain.category.repository;

import com.sparta.java2.project.part1.commerce.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}