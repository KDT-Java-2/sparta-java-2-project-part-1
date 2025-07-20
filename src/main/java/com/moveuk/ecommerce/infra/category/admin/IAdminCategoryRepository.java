package com.moveuk.ecommerce.infra.category.admin;

import com.moveuk.ecommerce.domain.category.Category;
import com.moveuk.ecommerce.domain.category.admin.AdminCategoryRepository;
import com.moveuk.ecommerce.infra.category.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class IAdminCategoryRepository implements AdminCategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return jpaCategoryRepository.findById(id);
    }

    @Override
    public void createCategory(Category category) {
        jpaCategoryRepository.save(category);
    }

    @Override
    public boolean existsByParent(Category category) {
        return jpaCategoryRepository.existsByParent(category);
    }
}
