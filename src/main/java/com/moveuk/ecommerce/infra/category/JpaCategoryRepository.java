package com.moveuk.ecommerce.infra.category;

import com.moveuk.ecommerce.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
