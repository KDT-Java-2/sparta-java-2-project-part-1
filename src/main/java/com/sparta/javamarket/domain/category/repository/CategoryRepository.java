package com.sparta.javamarket.domain.category.repository;

import com.sparta.javamarket.domain.category.dto.CategoryResponse;
import com.sparta.javamarket.domain.category.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Category findByName(String name);

  List<Category> findAll();

  List<Category> findAllById(Long parentId);

}
