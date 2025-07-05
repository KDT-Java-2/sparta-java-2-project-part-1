package com.moveuk.ecommerce.domain.category.admin;

import com.moveuk.ecommerce.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final AdminCategoryRepository adminCategoryRepository;

    public Category findById(Long aLong) {
        return null;
    }

    public void createCategory(Category category) {
    }
}
