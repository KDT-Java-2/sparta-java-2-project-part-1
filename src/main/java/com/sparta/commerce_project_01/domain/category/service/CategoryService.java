package com.sparta.commerce_project_01.domain.category.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import com.sparta.commerce_project_01.common.enums.exception.ServiceExceptionCode;
import com.sparta.commerce_project_01.domain.category.dto.CategoryRequest;
import com.sparta.commerce_project_01.domain.category.dto.CategoryResponse;
import com.sparta.commerce_project_01.domain.category.entity.Category;
import com.sparta.commerce_project_01.domain.category.mapper.CategoryMapper;
import com.sparta.commerce_project_01.domain.category.repository.CategoryRepository;
import com.sparta.commerce_project_01.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

  private final Jedis jedis;
  private final ObjectMapper objectMapper = new ObjectMapper();

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final CategoryMapper categoryMapper;

  private static final String CACHE_KEY_CATEGORY_STRUCT = "categoryStruct";
  private static final int CACHE_EXPIRE_SECONDS = 3600;  // 1시간

  @Transactional
  public Long save(CategoryRequest request) {
    Category parentCategory = findParentCategory(request.getParentId());

    Category savedCategory = categoryRepository.save(Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .parent(parentCategory)
        .build());

    return savedCategory.getId();
  }

  @Transactional
  public CategoryResponse update(Long id, CategoryRequest request) {
    Category category = findCategoryById(id);
    Category parentCategory = findParentCategory(request.getParentId());

    // 자기 자신을 부모로 지정하는 순환 참조가 발생하지 않도록 검증
    if (parentCategory != null && parentCategory.equals(category)) {
      throw new ServiceException(ServiceExceptionCode.CATEGORY_INVAILD_PARENTID);
    }

    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setParent(parentCategory);

    categoryRepository.save(category);

    return CategoryResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .description(category.getDescription())
        .parentId(category.getParent() == null ? null : category.getParent().getId())
        .build();
  }

  @Transactional
  public void delete(Long id) {
    Category category = findParentCategory(id);

    // 하위에 다른 케테고리가 없어야 한다
    if (!category.getChildren().isEmpty()) {
      throw new ServiceException(ServiceExceptionCode.CATEGORY_DELETE_FAIL);
    }

    // 카테고리에 속한 상품이 없어야 한다
    if (productRepository.existsByCategoryId(id)) {
      throw new ServiceException(ServiceExceptionCode.CATEGORY_DELETE_FAIL);
    }

    categoryRepository.delete(category);

  }

  private Category findParentCategory(Long parentId) {
    if (parentId == null || parentId == 0L) {
      return null;
    }
    return categoryRepository.findById(parentId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));
  }

  private Category findCategoryById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));
  }

  private List<CategoryResponse> findCategoryStruct() {
    List<CategoryResponse> rootCategories = new ArrayList<>();
    List<Category> categories = categoryRepository.findAll();

    categories.forEach(category -> {
      if (category.getParent() == null) {
        rootCategories.add(CategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .description(category.getDescription())
            .parentId(category.getParent() == null ? null : category.getParent().getId())
            .build());
      }
    });

    return rootCategories;
  }

  @Transactional(readOnly = true)
  public List<CategoryResponse> findCategoryStructCacheAside() throws JsonProcessingException {
    // 1. 캐시에서 카테고리 구조 데이터 조회 시도
    String cachedCategories = jedis.get(CACHE_KEY_CATEGORY_STRUCT);

    // 2. 캐시 히트
    if (!ObjectUtils.isEmpty(cachedCategories)) {
      System.out.println("Cache Hit: categoryStruct for key " + CACHE_KEY_CATEGORY_STRUCT);
      return objectMapper.readValue(cachedCategories, new TypeReference<>() {
      });
    }

    // 3. 캐시 미스, 데이터베이스에서 조회 (findCategoryStruct() 호출)
    System.out.println("Cache Miss: categoryStruct for key " + CACHE_KEY_CATEGORY_STRUCT);
    List<CategoryResponse> rootCategories = findCategoryStruct();

    // 4. 데이터베이스에서 조회한 데이터를 캐시에 저장
    if (!ObjectUtils.isEmpty(rootCategories)) {
      String jsonString = objectMapper.writeValueAsString(rootCategories);
      jedis.setex(CACHE_KEY_CATEGORY_STRUCT, CACHE_EXPIRE_SECONDS, jsonString);
    }

    return rootCategories; // 데이터베이스에서 조회한 데이터 반환
  }

  @Transactional
  public void saveWriteThrough(CategoryRequest request) {
    Category parentCategory = null;

    if (ObjectUtils.isEmpty(request.getParentId())) {
      parentCategory = categoryRepository.findById(request.getParentId())
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));
    }

    Category category = Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .parent(parentCategory)
        .build();

    categoryRepository.save(category);

    updateCategoryStructCache();
  }

  private void updateCategoryStructCache() {
    try {
      List<CategoryResponse> rootCategories = findCategoryStruct();

      if (!ObjectUtils.isEmpty(rootCategories)) {
        String jsonString = objectMapper.writeValueAsString(rootCategories);
        jedis.setex(CACHE_KEY_CATEGORY_STRUCT, CACHE_EXPIRE_SECONDS, jsonString);
      }
    } catch (Exception e) {
      log.error("Error updating cache key {}: {}", CACHE_KEY_CATEGORY_STRUCT, e.getMessage());
    }
  }

  @Transactional
  public void saveWriteBack(CategoryRequest request) {
    try {
      String cachedData = jedis.get(CACHE_KEY_CATEGORY_STRUCT);
      List<CategoryResponse> categories = new ArrayList<>();

      if (StringUtils.hasText(cachedData)) {
        categories = objectMapper.readValue(cachedData, new TypeReference<>() {
        });
      }

      CategoryResponse newCategory = CategoryResponse.builder()
          .name(request.getName())
          .description(request.getDescription())
          .categories(new ArrayList<>())
          .build();

      categories.add(newCategory);

      String jsonString = objectMapper.writeValueAsString(categories);
      jedis.setex(CACHE_KEY_CATEGORY_STRUCT, CACHE_EXPIRE_SECONDS, jsonString);

      saveToDatabaseAsync(request);

    } catch (Exception e) {
      log.error("Write-back 패턴 저장 실패: {}", e.getMessage(), e);
    }
  }

  @Async
  public void saveToDatabaseAsync(CategoryRequest request) {
    try {
      Category parentCategory = null;

      if (ObjectUtils.isEmpty(request.getParentId())) {
        parentCategory = categoryRepository.findById(request.getParentId())
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));
      }

      Category newCategory = Category.builder()
          .name(request.getName())
          .description(request.getDescription())
          .parent(parentCategory)
          .build();

      categoryRepository.save(newCategory);

    } catch (Exception e) {
      log.error("비동기 DB 저장 실패: {}", e.getMessage(), e);
    }
  }

  public List<CategoryResponse> findAllCategories() {
    List<Category> categories = categoryRepository.findAll();

    Map<Long, CategoryResponse> categoryMap = categories.stream()
        .map(categoryMapper::toCategoryResponse) // 엔티티를 DTO로 변환
        .collect(Collectors.toMap(CategoryResponse::getId, dto -> dto));

    List<CategoryResponse> rootCategories = new ArrayList<>(); // 최상위 카테고리들을 담을 리스트

    // 3. 트리 구조를 재조립합니다.
    // 모든 DTO를 순회하면서 부모-자식 관계를 연결합니다.
    for (CategoryResponse categoryDto : categoryMap.values()) {
      if (categoryDto.getParentId() == null || categoryDto.getParentId() == 0) {
        // 부모 ID가 null이면 최상위 카테고리입니다.
        rootCategories.add(categoryDto);
      } else {
        // 부모 ID가 있으면 해당 부모 DTO를 찾아 자식 리스트에 추가합니다.
        CategoryResponse parentDto = categoryMap.get(categoryDto.getParentId());
        if (parentDto != null) {
          // 부모 DTO가 맵에 존재하는 경우에만 자식으로 추가 (데이터 무결성 문제 방지)
          parentDto.getChildren().add(categoryDto);
        }
        // 만약 parentDto가 null이라면, 해당 자식 카테고리는 유효하지 않은 부모 ID를 가지고 있거나
        // 부모 카테고리가 데이터베이스에 없는 경우입니다. (이 경우 해당 자식은 트리에 포함되지 않음)
      }
    }

    // 4. (선택 사항) 트리 구조의 각 레벨에서 카테고리들을 정렬합니다.
    // 예를 들어, 이름 순으로 정렬할 수 있습니다.
    sortCategoryTree(rootCategories);

    return rootCategories;
  }

  private void sortCategoryTree(List<CategoryResponse> categories) {
    if (categories == null || categories.isEmpty()) {
      return;
    }
    // 현재 레벨의 카테고리들을 이름 순으로 정렬
    categories.sort(Comparator.comparing(CategoryResponse::getName));

    // 각 카테고리의 자식 리스트도 재귀적으로 정렬
    for (CategoryResponse category : categories) {
      sortCategoryTree(category.getChildren());
    }
  }

}