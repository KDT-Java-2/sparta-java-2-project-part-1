package com.sparta.java_02.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * 카테고리명으로 조회
     */
    Optional<Category> findByName(String name);

    /**
     * 최상위 카테고리 조회 (부모가 없는 카테고리)
     */
    @Query("SELECT c FROM Category c WHERE c.parent IS NULL AND c.isActive = true ORDER BY c.displayOrder")
    List<Category> findRootCategoriesOrderByDisplayOrder();

    /**
     * 특정 부모 카테고리의 하위 카테고리 조회
     */
    @Query("SELECT c FROM Category c WHERE c.parent.id = :parentId AND c.isActive = true ORDER BY c.displayOrder")
    List<Category> findByParentIdAndIsActiveTrueOrderByDisplayOrder(@Param("parentId") Long parentId);

    /**
     * 활성 상태의 모든 카테고리 조회
     */
    List<Category> findByIsActiveTrueOrderByDisplayOrder();

    /**
     * 계층 구조 전체 조회 (부모-자식 관계 포함)
     */
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.children WHERE c.parent IS NULL AND c.isActive = true ORDER BY c.displayOrder")
    List<Category> findRootCategoriesWithChildren();

    /**
     * 특정 카테고리의 모든 상위 카테고리 조회
     */
    @Query("SELECT c FROM Category c WHERE c.id IN " +
           "(SELECT DISTINCT parent.id FROM Category child " +
           "WHERE child.id = :categoryId AND child.parent IS NOT NULL)")
    List<Category> findParentCategories(@Param("categoryId") Long categoryId);

    /**
     * 카테고리명 중복 확인
     */
    boolean existsByName(String name);

    /**
     * 특정 부모 하위의 카테고리 개수
     */
    long countByParentId(Long parentId);

    /**
     * 카테고리에 속한 활성 상품이 있는지 확인
     */
    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.category.id = :categoryId")
    boolean hasActiveProducts(@Param("categoryId") Long categoryId);
} 