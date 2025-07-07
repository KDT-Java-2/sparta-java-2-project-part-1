package com.sparta.commerce.domain.category.service;

import com.sparta.commerce.domain.category.dto.CategoryRequest;
import com.sparta.commerce.domain.category.dto.CategoryResponse;
import com.sparta.commerce.domain.category.dto.CategoryTreeDto;
import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.category.mapper.CategoryMapper;
import com.sparta.commerce.domain.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final CategoryValidator categoryValidator;
  private final CategoryMapper categoryMapper;

  // 카테고리 생성
  @Transactional
  public Long create(CategoryRequest request){
    Category parent = categoryValidator.validateExistParentCategory(request.getParentId());

    int depth = (parent == null) ? 0 : parent.getDepth() + 1;

    Category category = Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .depth(depth)
        .parent(parent)
        .build();
    category.updateParent(parent);
    category.updateDepth(depth);

    Category saved = categoryRepository.save(category);
    return saved.getId();
  }

  // 카테고리 조회
  @Transactional
  public List<CategoryTreeDto> getAllCategory(){
    List<Category> categories = categoryRepository.findAll();
    List<CategoryResponse> flatDto = categories.stream()
        .map(categoryMapper::toResponse)
        .toList();
    return convertToTree(flatDto);
  }

  // 카테고리 수정
  @Transactional
  public CategoryResponse update(Long categoryId, CategoryRequest request) {
    categoryValidator.validateParentCategoryNotSelf(categoryId, request);
    Category category = categoryValidator.getCategory(categoryId);
    Category parent = categoryValidator.validateExistParentCategory(request.getParentId());

    category.updateParent(parent);
    category.updateName(request.getName());
    category.updateDescription(request.getDescription());
    return categoryMapper.toResponse(category);
  }

  // 카테고리 삭제
  @Transactional
  public void delete(Long categoryId){
    Category category = categoryValidator.getCategory(categoryId);
    categoryValidator.validateNoProductsInCategory(category);
    categoryValidator.validateNoChildCategories(category);
    categoryRepository.delete(category);
  }


  private List<CategoryTreeDto> convertToTree(List<CategoryResponse> flatDto) {
    HashMap<Long, CategoryTreeDto> nodeMap = new HashMap<>();

    for (CategoryResponse flat : flatDto) {
      CategoryTreeDto treeNode = new CategoryTreeDto(flat.getId(), flat.getName());
      nodeMap.put(flat.getId(), treeNode);
    }

    List<CategoryTreeDto> rootNodes = new ArrayList<>();

    for (CategoryResponse flat : flatDto) {
      CategoryTreeDto currentNode = nodeMap.get(flat.getId());

      if (flat.getParentId() == null) {
        rootNodes.add(currentNode);
      } else {
        CategoryTreeDto parentNode = nodeMap.get(flat.getParentId());
        if (parentNode != null) {
          parentNode.addChild(currentNode);
        }
      }
    }
    return rootNodes;
  }
}
