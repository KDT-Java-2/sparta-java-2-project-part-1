package com.sparta.bootcamp.java_2_example.domain.category.service;

import com.sparta.bootcamp.java_2_example.common.exception.ServiceException;
import com.sparta.bootcamp.java_2_example.common.exception.ServiceExceptionCode;
import com.sparta.bootcamp.java_2_example.domain.category.dto.*;
import com.sparta.bootcamp.java_2_example.domain.category.entity.Category;
import com.sparta.bootcamp.java_2_example.domain.category.mapper.CategoryMapper;
import com.sparta.bootcamp.java_2_example.domain.category.repository.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryHierarchyResponse> getAllHierarchy() {
        return getCategoryHierarchy();
    }

    @Transactional
    public CategoryCreateResponse save(CategoryCreateRequest request) {
        Category category = categoryMapper.toEntity(request);

        if (!ObjectUtils.isEmpty(request.getParentId())) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

            category.setParent(parent);
        }

        categoryRepository.save(category);
        return categoryMapper.toCreateResponse(category);
    }

    @Transactional
    public CategoryResponse update(Long categoryId, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        if (!ObjectUtils.isEmpty(request.getParentId())) {
            ensureNotSelfReference(categoryId, request.getParentId());

            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

            category.setParent(parent);
        }

        return categoryMapper.toResponse(category);
    }

    private List<CategoryHierarchyResponse> getCategoryHierarchy() {
        List<Category> categories = categoryRepository.findAll();

        Map<Long, CategoryHierarchyResponse> responseMap = new HashMap<>();

        for (Category category : categories) {
            CategoryHierarchyResponse response = CategoryHierarchyResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .children(new ArrayList<>())
                    .build();

            responseMap.put(category.getId(), response);
        }

        List<CategoryHierarchyResponse> rootCategories = new ArrayList<>();
        for (Category category : categories) {
            CategoryHierarchyResponse response = responseMap.get(category.getId());

            if (ObjectUtils.isEmpty(category.getParent())) {
                rootCategories.add(response);
            } else {
                CategoryHierarchyResponse parentResponse = responseMap.get(category.getParent().getId());
                if (!ObjectUtils.isEmpty(parentResponse)) {
                    parentResponse.getChildren().add(response);
                }
            }
        }
        return rootCategories;
    }

    private void ensureNotSelfReference(Long categoryId, Long parentId) {
        if (Objects.equals(categoryId, parentId)) {
            throw new ServiceException(ServiceExceptionCode.INVALID_PARENT_CATEGORY);
        }
    }

}
