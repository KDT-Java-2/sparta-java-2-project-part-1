package com.sparta.project1.domain.product.service.admin;

import com.sparta.project1.common.exception.ServiceException;
import com.sparta.project1.common.exception.ServiceExceptionCode;
import com.sparta.project1.domain.category.entity.Category;
import com.sparta.project1.domain.category.repository.CategoryRepository;
import com.sparta.project1.domain.product.dto.admin.ProductAdminRegisterRequest;
import com.sparta.project1.domain.product.dto.admin.ProductAdminRegisterResponse;
import com.sparta.project1.domain.product.entity.Product;
import com.sparta.project1.domain.product.mapper.ProductAdminMapper;
import com.sparta.project1.domain.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ProductAdminService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductAdminMapper productAdminMapper;

    @Transactional
    public ProductAdminRegisterResponse productAdminRegister(@Valid @RequestBody ProductAdminRegisterRequest request) {
        //상품명 중복검사
        if(ObjectUtils.isEmpty(request.getName())) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_NAME);
        }

        //카테고리 조회
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        Product product = productRepository.save(Product.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .price(request.getPrice())
                        .stock(request.getStock())
                        .category(category)
                        .build());
        
        return productAdminMapper.toResponse(product);
    }
}
