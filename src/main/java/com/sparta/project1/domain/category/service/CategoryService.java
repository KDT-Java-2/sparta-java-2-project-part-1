package com.sparta.project1.domain.category.service;

import com.sparta.project1.common.exception.ServiceException;
import com.sparta.project1.common.exception.ServiceExceptionCode;
import com.sparta.project1.domain.category.dto.CategoryRequest;
import com.sparta.project1.domain.category.dto.CategoryResponse;
import com.sparta.project1.domain.category.entity.Category;
import com.sparta.project1.domain.category.mapper.CategoryMapper;
import com.sparta.project1.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Transactional
  public CategoryResponse create(CategoryRequest request) {
    Category category = null; //최상위카테고리는 null로 구분하기에 null도 셋팅되어야한다.

    //parentId 존재 시 부모카테고리 조회
    if(!ObjectUtils.isEmpty(request.getParentId())) {
      category = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }

    Category saved = categoryRepository.save(Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .parent(category)
        .build());

    return categoryMapper.toResponse(saved);
  }

  @Transactional
  public Category update(Long categoryId, CategoryRequest request) {
    Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    Category parentId = null; //최상위카테고리는 null로 구분하기에 null도 셋팅되어야한다.

    //parentId 존재 시 부모카테고리 조회
    if(!ObjectUtils.isEmpty(request.getParentId())) {
      parentId = categoryRepository.findById(request.getParentId())
              .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }

    if(Objects.equals(request.getParentId(), categoryId)) {
      throw new IllegalArgumentException("자기자신을 부모로 지정할수 없습니다.");
    }

    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setParent(parentId);

    return category;
  }

  @Transactional
  public void delete(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    categoryRepository.delete(category);
  }
}
