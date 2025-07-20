package com.sparta.ecommerce.domain.category.repository;

import com.sparta.ecommerce.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  boolean existsByParentId(Long parentId);
}
