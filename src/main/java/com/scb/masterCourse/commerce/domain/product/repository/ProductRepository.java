package com.scb.masterCourse.commerce.domain.product.repository;

import com.scb.masterCourse.commerce.domain.category.entity.Category;
import com.scb.masterCourse.commerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByCategory(Category category);
}
