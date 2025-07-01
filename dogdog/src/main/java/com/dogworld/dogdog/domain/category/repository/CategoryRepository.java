package com.dogworld.dogdog.domain.category.repository;

import com.dogworld.dogdog.domain.category.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  @Query(value = "SELECT id, name, parent_id, depth, sort_order, active FROM category", nativeQuery = true)
  List<FlatCategoryDto> findAllFlat();
}
