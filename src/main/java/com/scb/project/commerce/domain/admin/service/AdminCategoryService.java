package com.scb.project.commerce.domain.admin.service;

import com.scb.project.commerce.common.exception.ServiceException;
import com.scb.project.commerce.common.exception.ServiceExceptionCode;
import com.scb.project.commerce.domain.admin.dto.AdminCategoryCreateRequest;
import com.scb.project.commerce.domain.admin.dto.AdminCategoryResponse;
import com.scb.project.commerce.domain.admin.dto.AdminCategoryUpdateRequest;
import com.scb.project.commerce.domain.admin.mapper.AdminCategoryMapper;
import com.scb.project.commerce.domain.category.entity.Category;
import com.scb.project.commerce.domain.category.repository.CategoryRepository;
import com.scb.project.commerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final AdminCategoryMapper adminCategoryMapper;

    /**
     * 카테고리 등록 서비스
     *
     * <p>부모 카테고리가 존재할 경우, 해당 부모에 속한 하위 카테고리로 등록됩니다.</p>
     * <p>부모 ID가 없거나 null이면 최상위 카테고리로 등록됩니다.</p>
     *
     * @param request 카테고리 등록 요청 DTO
     * @return 생성된 카테고리 ID
     */
    @Transactional
    public Long createCategory(AdminCategoryCreateRequest request) {

        Category parent = getCategory(request.getParentId());

        Category category = adminCategoryMapper.toEntity(request, parent);

        Category savedCategory = categoryRepository.save(category);

        return savedCategory.getId();
    }

    /**
     * 카테고리 수정 서비스
     *
     * <p>카테고리명 또는 부모 카테고리를 수정합니다.</p>
     * <p>자기 자신을 부모로 지정할 수 없으며, 부모 ID가 유효하지 않으면 예외가 발생합니다.</p>
     *
     * @param categoryId 수정 대상 카테고리 ID
     * @param request    카테고리 수정 요청 DTO
     * @return 수정된 카테고리 응답 DTO
     */
    @Transactional
    public AdminCategoryResponse updateCategory(Long categoryId,
        AdminCategoryUpdateRequest request) {

        Category category = getCategory(categoryId);

        category.setName(request.getName());

        // 부모 카테고리 null 체크
        if (request.getParentId() != null) {
            Category parent = getCategory(request.getParentId());

            // 자기 참조 시 에러 발생
            if (parent.getId()
                .equals(category.getId())) {
                throw new ServiceException(ServiceExceptionCode.INVALID_PARENT_SELF_REFERENCE);
            }

            category.setParent(parent);
        }

        return adminCategoryMapper.toResponse(category);
    }

    /**
     * 카테고리 삭제 서비스
     *
     * <P>하위 카테고리 또는 상품이 존재하는 경우 삭제할 수 없습니다.</P>
     *
     * @param categoryId 삭제 대상 카테고리 ID
     */
    @Transactional
    public void deleteCategory(Long categoryId) {

        Category category = getCategory(categoryId);

        // 하위에 다른 카테고리 있으면 삭제 불가능
        boolean isExistsChild = categoryRepository.existsByParent(category);
        if (isExistsChild) {
            throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_HAS_CHILD);
        }

        // 카테고리에 속한 상품이 있으면 삭제 불가능
        boolean isExistsProduct = productRepository.existsByCategory(category);
        if (isExistsProduct) {
            throw new ServiceException(ServiceExceptionCode.CANNOT_DELETE_HAS_PRODUCT);
        }

        categoryRepository.delete(category);
    }


    // 카테고리 객체 반환 메서드
    private Category getCategory(Long categoryId) {

        // null 체크
        if (categoryId == null) {
            return null;
        }

        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));
    }
}
