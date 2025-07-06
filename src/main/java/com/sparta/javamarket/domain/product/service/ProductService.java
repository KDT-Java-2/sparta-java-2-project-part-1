package com.sparta.javamarket.domain.product.service;

import com.sparta.javamarket.common.exception.ServiceException;
import com.sparta.javamarket.common.exception.ServiceExceptionCode;
import com.sparta.javamarket.domain.category.entity.Category;
import com.sparta.javamarket.domain.category.repository.CategoryRepository;
import com.sparta.javamarket.domain.product.dto.ProductCreateRequest;
import com.sparta.javamarket.domain.product.dto.ProductResponse;
import com.sparta.javamarket.domain.product.dto.ProductSearchResponse;
import com.sparta.javamarket.domain.product.entity.Product;
import com.sparta.javamarket.domain.product.mapper.ProductMapper;
import com.sparta.javamarket.domain.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ProductMapper productMapper;

  public List<ProductSearchResponse> getAllProducts(){
    return productRepository.findAll().stream()
        .map(productMapper::toResponse)
        .toList();
  }

  public ProductSearchResponse getProductById(long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    return ProductSearchResponse.builder()
        .id(product.getId())
//        .categoryId(product.getCategoryId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .createdAt(product.getCreatedAt())
        .build();
  }
  @Transactional
  public ProductSearchResponse save(ProductCreateRequest request) {
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    return productMapper.toResponse(productRepository.save(Product.builder()
        .category(category)
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .stock(request.getStock())
        .build()));
  }

}
