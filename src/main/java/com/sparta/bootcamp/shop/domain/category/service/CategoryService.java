package com.sparta.bootcamp.shop.domain.category.service;

import com.sparta.bootcamp.shop.domain.category.dto.CategoryRequest;
import com.sparta.bootcamp.shop.domain.category.entity.Category;
import com.sparta.bootcamp.shop.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void save(CategoryRequest request) {
        categoryRepository.save(Category.builder()
                .name(request.getName())
                .build());
    }

}