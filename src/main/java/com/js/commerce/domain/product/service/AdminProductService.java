package com.js.commerce.domain.product.service;

import com.js.commerce.common.exception.ServiceException;
import com.js.commerce.common.exception.ServiceExceptionCode;
import com.js.commerce.domain.category.entity.Category;
import com.js.commerce.domain.category.repository.CategoryRepository;
import com.js.commerce.domain.product.dto.admin.AdminProductCreateRequest;
import com.js.commerce.domain.product.dto.admin.AdminProductCreateResponse;
import com.js.commerce.domain.product.entity.Product;
import com.js.commerce.domain.product.mapper.ProductMapper;
import com.js.commerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  private final ProductMapper productMapper;

  private final CreateProductValidator createProductValidator;

  @Transactional
  public AdminProductCreateResponse createProduct(AdminProductCreateRequest request) {

    createProductValidator.validateRequest(request);

    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));

    Product product = productMapper.toEntity(request, category);
    Product saved = productRepository.save(product);
    
    return new AdminProductCreateResponse(saved.getId());

  }

}
