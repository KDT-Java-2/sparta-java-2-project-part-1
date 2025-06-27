package com.sparta.java2.project.part1.commerce.domain.product.service;

import com.sparta.java2.project.part1.commerce.domain.product.dto.CategoryProductDto;
import com.sparta.java2.project.part1.commerce.domain.product.repository.CategoryProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryProductQueryRepository categoryProductQueryRepository;

    public Page<CategoryProductDto> findCategoryProductByName(String categoryName, Pageable pageable) {
        return categoryProductQueryRepository.findCategoryProductByName(categoryName, pageable);
    }
}
