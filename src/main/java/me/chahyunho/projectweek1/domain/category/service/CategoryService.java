package me.chahyunho.projectweek1.domain.category.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.common.enums.exception.ServiceException;
import me.chahyunho.projectweek1.common.enums.exception.ServiceExceptionCode;
import me.chahyunho.projectweek1.domain.category.dto.CategoryRequest;
import me.chahyunho.projectweek1.domain.category.dto.CategorySearchResponse;
import me.chahyunho.projectweek1.domain.category.entity.Category;
import me.chahyunho.projectweek1.domain.category.repository.CategoryRepository;
import me.chahyunho.projectweek1.domain.product.repository.CategoryProductQueryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryProductQueryRepository categoryProductQueryRepository;

  public void create(CategoryRequest request) {
    Category parent = null;
    if (request.getParentId() != null) {
      parent = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUNT_CATEGORY_PARENT));
    }

    Category category = Category.builder()
        .name(request.getName())
        .parent(parent)
        .build();

    categoryRepository.save(category);
  }

  @Transactional
  public List<CategorySearchResponse> searchCategoryHierarchy() {
    List<Category> categories = categoryRepository.findAll();

    long mapStart = System.nanoTime();
    List<CategorySearchResponse> topNode = new ArrayList<>();
    Map<Long, CategorySearchResponse> treeMap = new HashMap<>();

    for (Category flat : categories) {
      CategorySearchResponse categoryTreeDto = new CategorySearchResponse();
      categoryTreeDto.setId(flat.getId());
      categoryTreeDto.setName(flat.getName());
      treeMap.put(flat.getId(), categoryTreeDto);
    }

    for (Category flat : categories) {
      CategorySearchResponse node = treeMap.get(flat.getId());

      if (flat.getParent() == null) {
        topNode.add(node);
      } else {
        CategorySearchResponse parent = treeMap.get(flat.getParent().getId());
        parent.getChildren().add(node);
      }
    }

    return topNode;
  }

}
