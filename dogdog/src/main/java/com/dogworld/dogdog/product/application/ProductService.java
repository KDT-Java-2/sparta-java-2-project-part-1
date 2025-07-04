package com.dogworld.dogdog.product.application;

import com.dogworld.dogdog.product.interfaces.dto.request.ProductRequest;
import com.dogworld.dogdog.product.interfaces.dto.request.ProductSearchCondition;
import com.dogworld.dogdog.product.interfaces.dto.response.ProductResponse;
import com.dogworld.dogdog.category.domain.Category;
import com.dogworld.dogdog.category.domain.repository.CategoryRepository;
import com.dogworld.dogdog.product.domain.Product;
import com.dogworld.dogdog.product.infrastructure.ProductQueryRepository;
import com.dogworld.dogdog.product.domain.repository.ProductRepository;
import com.dogworld.dogdog.global.common.QueryDslConfig;
import com.dogworld.dogdog.global.error.code.ErrorCode;
import com.dogworld.dogdog.global.error.exception.CustomException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductQueryRepository productQueryRepository;
  private final CategoryRepository categoryRepository;
  private final QueryDslConfig queryDslConfig;

  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();

    return products.stream()
        .map(ProductResponse::from)
        .collect(Collectors.toList());
  }

  public Page<ProductResponse> searchProducts(ProductSearchCondition condition, Pageable pageable) {
    Page<Product> search = productQueryRepository.search(condition, pageable);
    return search.map(ProductResponse::from);
  }

  @Transactional
  public ProductResponse createProduct(ProductRequest request) {
    validateDuplicateName(request.getName());

    Category category = getCategory(request.getCategoryId());
    Product createdProduct = Product.create(request, category);
    Product savedProduct = productRepository.save(createdProduct);
    return ProductResponse.from(savedProduct);
  }

  public ProductResponse getProductById(Long productId) {
    Product product = getProduct(productId);
    return ProductResponse.from(product);
  }

  @Transactional
  public ProductResponse updateProduct(Long productId, ProductRequest request) {
    Product product = getProduct(productId);
    Category category = getCategory(request.getCategoryId());
    product.update(request, category);
    return ProductResponse.from(product);
  }

  @Transactional
  public void deleteProduct(Long productId) {
    Product product = getProduct(productId);

    if(productQueryRepository.isIncludeInCompletedOrder(productId)) {
      throw new CustomException(ErrorCode.PRODUCT_CANNOT_BE_DELETED);
    }
    productRepository.delete(product);
  }

  private Product getProduct(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
  }

  private void validateDuplicateName(String name) {
    if(productRepository.existsByName(name)) {
      throw new CustomException(ErrorCode.DUPLICATED_PRODUCT_NAME);
    }
  }

  private Category getCategory(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));
  }


}
