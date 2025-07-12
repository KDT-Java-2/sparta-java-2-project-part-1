package com.moveuk.ecommerce.domain.category.admin;

import com.moveuk.ecommerce.domain.category.Category;

import java.util.Optional;

public interface AdminCategoryRepository {
    Optional<Category> findById(Long id);

    void createCategory(Category category);

    boolean existsByParent(Category category);
}
