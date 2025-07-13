package com.sparta.bootcamp.shop.domain.category.service;

import com.sparta.bootcamp.shop.common.exception.ServiceException;
import com.sparta.bootcamp.shop.common.exception.ServiceExceptionCode;
import com.sparta.bootcamp.shop.domain.category.dto.CategoryRequest;
import com.sparta.bootcamp.shop.domain.category.entity.Category;
import com.sparta.bootcamp.shop.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(CategoryRequest request) {

        Category parentCategory = null;

        if (request.getParentId() != null) {
            parentCategory = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
        }

        Category category = categoryRepository.save(Category.builder()
                .name(request.getName())
                .parent(parentCategory)
                .build());

        return category.getId();
    }

    @Transactional
    public void edit(Long categoryId, CategoryRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        Category parentCategory = null;

        if (request.getParentId() != null) {
            parentCategory = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

            if (Objects.equals(parentCategory.getId(), category.getId())) {
                throw new ServiceException(ServiceExceptionCode.INVALID_CATEGORY_DATA);
            }
        }

        category.setName(request.getName());
        category.setParent(parentCategory);

    }

    @Transactional
    public void delete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        categoryRepository.delete(category);
    }

}
