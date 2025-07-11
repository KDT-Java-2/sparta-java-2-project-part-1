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
}
