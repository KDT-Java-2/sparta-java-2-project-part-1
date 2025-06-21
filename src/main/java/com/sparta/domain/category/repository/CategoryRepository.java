package com.sparta.domain.category.repository;

import com.sparta.domain.category.entity.Category;
import com.sparta.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
