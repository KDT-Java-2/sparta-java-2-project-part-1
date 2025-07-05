package com.dogworld.dogdog.product.application;

import com.dogworld.dogdog.global.error.code.ErrorCode;
import com.dogworld.dogdog.global.error.exception.CustomException;
import com.dogworld.dogdog.product.domain.Product;
import com.dogworld.dogdog.product.domain.repository.ProductRepository;
import com.dogworld.dogdog.product.infrastructure.ProductQueryRepository;
import com.dogworld.dogdog.product.interfaces.dto.request.ProductSearchCondition;
import com.dogworld.dogdog.product.interfaces.dto.response.ProductResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductQueryService {
  private final ProductRepository productRepository;
  private final ProductQueryRepository productQueryRepository;

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

  public ProductResponse getProductById(Long productId) {
    Product product = getProduct(productId);
    return ProductResponse.from(product);
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
}
