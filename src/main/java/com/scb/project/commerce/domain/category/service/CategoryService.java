package com.scb.project.commerce.domain.category.service;

import com.scb.project.commerce.domain.category.dto.CategoryResponse;
import com.scb.project.commerce.domain.category.entity.Category;
import com.scb.project.commerce.domain.category.repository.CategoryQueryRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryQueryRepository categoryQueryRepository;

    /**
     * 카테고리 전체 계층 구조 조회 서비스
     *
     * <p>DB에서 모든 카테고리와 부모 정보를 한 번에 조회한 후,</p>
     * <p>각 카테고리를 CategoryResponse 형태로 변환하고,</p>
     * <p>부모-자식 관계를 기반으로 트리 구조를 구성하여 반환합니다.</p>
     *
     * @return 트리 구조의 카테고리 응답 리스트
     */
    public List<CategoryResponse> getCategoryHierarchy() {
        List<Category> categories = categoryQueryRepository.findCategoryHierarchy();

        Map<Long, CategoryResponse> map = new HashMap<>();
        for (Category category : categories) {
            // 카테고리를 DTO로 변환해서 map에 저장 (id → dto)
            map.put(category.getId(),
                new CategoryResponse(category.getId(), category.getName()));
        }

        // 트리 구성
        List<CategoryResponse> roots = new ArrayList<>();
        for (Category category : categories) {
            // 트리 구조로 자식 노드를 부모의 children 리스트에 연결
            CategoryResponse dto = map.get(category.getId());

            // 부모가 없으면 루트 노드로 추가
            if (category.getParent() == null) {
                roots.add(dto);
            } else {
                // 부모가 있으면 부모의 children에 현재 노드를 추가
                CategoryResponse parent = map.get(category.getParent()
                    .getId());
                parent.getChildren()
                    .add(dto);
            }
        }

        return roots;
    }
}
