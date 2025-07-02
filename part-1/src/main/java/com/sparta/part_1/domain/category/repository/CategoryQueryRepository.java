package com.sparta.part_1.domain.category.repository;

import static com.sparta.part_1.domain.category.entity.QCategory.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.part_1.common.exception.CategoryErrorCode;
import com.sparta.part_1.common.exception.CategoryException;
import com.sparta.part_1.domain.category.dto.response.CategoryResponse;
import com.sparta.part_1.domain.category.entity.Category;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
@RequiredArgsConstructor
public class CategoryQueryRepository {

  private final JPAQueryFactory queryFactory;

  public List<CategoryResponse> findAll() {
    List<Category> headCategory = queryFactory.select(category)
        .from(category)
        .fetch();

    return mappingEntityToResponseDto(headCategory);

  }

  private List<CategoryResponse> mappingEntityToResponseDto(List<Category> allCategory) {
    if (category == null) {
      throw new CategoryException(CategoryErrorCode.HAS_NOT_FOUND_CATEGORY);
    }

    Map<Long, CategoryResponse> categoryMap = new HashMap<>();
    List<CategoryResponse> roots = new ArrayList<>();
    for (Category categoryEntity : allCategory) {
      categoryMap.put(categoryEntity.getId(),
          new CategoryResponse(categoryEntity.getId(), categoryEntity.getName(), new ArrayList<>()
          ));
    }

    for (Category categoryEntity : allCategory) {
      CategoryResponse categoryResponse = categoryMap.get(categoryEntity.getId());
      if (categoryEntity.getParent() != null) {
        CategoryResponse parentCategory = categoryMap.get(categoryEntity.getParent().getId());
        parentCategory.getChildren().add(categoryResponse);
      } else {
        roots.add(categoryResponse);
      }
    }

    return roots;
  }
}
