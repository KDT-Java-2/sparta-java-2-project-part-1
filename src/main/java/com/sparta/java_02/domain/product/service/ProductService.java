package com.sparta.java_02.domain.product.service;

import com.sparta.java_02.domain.category.entity.Category;
import com.sparta.java_02.domain.category.service.CategoryService;
import com.sparta.java_02.domain.product.dto.ProductCreateRequestDto;
import com.sparta.java_02.domain.product.dto.ProductResponseDto;
import com.sparta.java_02.domain.product.dto.ProductUpdateRequestDto;
import com.sparta.java_02.domain.product.entity.Product;
import com.sparta.java_02.domain.product.mapper.ProductMapper;
import com.sparta.java_02.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CategoryService categoryService; // CategoryService 주입

  // 상품 등록
  @Transactional
  public ProductResponseDto createProduct(ProductCreateRequestDto requestDto) {
    // CategoryService를 통해 카테고리 엔티티를 조회
    Category category = categoryService.getCategoryById(requestDto.getCategoryId());

    Product product = productMapper.toEntity(requestDto);
    // Product 엔티티에 Category를 설정 (필요에 따라 엔티티에 Setter 추가)
    // product.setCategory(category);

    product = productRepository.save(product);

    return productMapper.toResponseDto(product);
  }

  // 단일 상품 조회
  @Transactional
  public ProductResponseDto getProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

    return productMapper.toResponseDto(product);
  }

  // 전체 상품 조회
  @Transactional
  public List<ProductResponseDto> getProducts() {
    List<Product> products = productRepository.findAll();

    return products.stream()
        .map(productMapper::toResponseDto)
        .collect(Collectors.toList());
  }

  // 상품 수정
  @Transactional
  public ProductResponseDto updateProduct(Long productId, ProductUpdateRequestDto requestDto) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

    productMapper.updateEntityFromDto(requestDto, product);
    Product updatedProduct = productRepository.save(product);

    return productMapper.toResponseDto(updatedProduct);
  }

  // 상품 삭제
  @Transactional
  public void deleteProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

    productRepository.delete(product);
  }
}