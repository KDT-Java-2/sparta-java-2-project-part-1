package com.sparta.spartajava2projectpart1.domain.category.service;

import com.sparta.spartajava2projectpart1.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

}
