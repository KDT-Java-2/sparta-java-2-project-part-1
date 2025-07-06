package com.sparta.javamarket.domain.admin.service;

import com.sparta.javamarket.common.exception.ServiceException;
import com.sparta.javamarket.common.exception.ServiceExceptionCode;
import com.sparta.javamarket.domain.admin.dto.AdminCategoryCreateRequest;
import com.sparta.javamarket.domain.admin.dto.AdminCategoryCreateResponse;
import com.sparta.javamarket.domain.admin.dto.AdminCategoryUpdateResponse;
import com.sparta.javamarket.domain.admin.dto.AdminCreateResponse;
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

  @Transactional
  public AdminCategoryCreateResponse adminUpdateCategory(Long categoryId, AdminCategoryCreateRequest request){

    Category category = getCategory(categoryId);

    if(category.getId().equals(request.getParentId())){
      throw new ServiceException(ServiceExceptionCode.INVALID_CATEGORY_PARENT);
    }

    category.setName(request.getName());
//    category.setDescription(request.getDescription()); //컬럼없으니 생략
    category.setParent(getCategory(request.getParentId()));

    return adminMapper.toCategoryAdminResponse(
        categoryRepository.save(category)
    );
  }

  public Category getCategory(Long parentId){
    return categoryRepository.findById(parentId).orElseThrow(()-> new ServiceException(ServiceExceptionCode.NOT_FOUND_PARENT_CATEGORY));
  }
}
