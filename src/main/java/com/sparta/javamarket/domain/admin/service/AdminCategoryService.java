package com.sparta.javamarket.domain.admin.service;

import com.sparta.javamarket.common.exception.ServiceException;
import com.sparta.javamarket.common.exception.ServiceExceptionCode;
import com.sparta.javamarket.domain.admin.dto.AdminCategoryCreateRequest;
import com.sparta.javamarket.domain.admin.dto.AdminCategoryCreateResponse;
import com.sparta.javamarket.domain.admin.mapper.AdminMapper;
import com.sparta.javamarket.domain.category.entity.Category;
import com.sparta.javamarket.domain.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

  private final CategoryRepository categoryRepository;
  private final AdminMapper adminMapper;

  @Transactional
  public AdminCategoryCreateResponse adminCreateCategory(
      AdminCategoryCreateRequest request) {

    return adminMapper.toCategoryAdminResponse(categoryRepository.save(Category.builder()
            .name(request.getName())
//            .description(request.getDescription()) //테이블 안만들었음.
        .parent(getCategory(request.getParentId()))
        .build()));


  }

  public Category getCategory(Long parentId){
    return categoryRepository.findById(parentId).orElseThrow(()-> new ServiceException(ServiceExceptionCode.NOT_FOUND_PARENT_CATEGORY));
  }
}
