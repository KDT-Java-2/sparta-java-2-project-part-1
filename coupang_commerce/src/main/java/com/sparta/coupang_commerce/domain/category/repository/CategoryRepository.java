package com.sparta.coupang_commerce.domain.category.repository;

import com.sparta.coupang_commerce.domain.category.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

  @Query("SELECT c FROM Category c LEFT JOIN FETCH c.parent")
  List<Category> findAll();
}
