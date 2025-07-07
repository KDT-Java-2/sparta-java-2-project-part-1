package com.sparta.part_1.domain.product.service;


import com.sparta.part_1.common.exception.ProductErrorCode;
import com.sparta.part_1.common.exception.ProductServiceException;
import com.sparta.part_1.common.exception.PurchaseErrorCode;
import com.sparta.part_1.common.exception.PurchaseServiceException;
import com.sparta.part_1.domain.category.entity.Category;
import com.sparta.part_1.domain.category.repository.CategoryRepository;
import com.sparta.part_1.domain.product.dto.request.ProductRequest;
import com.sparta.part_1.domain.product.dto.request.ProductSearchRequest;
import com.sparta.part_1.domain.product.dto.response.ProductAddResponse;
import com.sparta.part_1.domain.product.dto.response.ProductDeleteResponse;
import com.sparta.part_1.domain.product.dto.response.ProductResponse;
import com.sparta.part_1.domain.product.dto.response.ProductUpdateResponse;
import com.sparta.part_1.domain.product.entity.Product;
import com.sparta.part_1.domain.product.repository.ProductQueryRepository;
import com.sparta.part_1.domain.product.repository.ProductRepository;
import com.sparta.part_1.domain.purchase.repository.PurchaseQueryRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductQueryRepository productQueryRepository;
  private final CategoryRepository categoryRepository;
  private final PurchaseQueryRepository purchaseQueryRepository;

  public Page<ProductResponse> findProducts(ProductSearchRequest request, Pageable pageable) {
    return productQueryRepository.searchProductsPage(request, pageable);
  }

  public List<ProductResponse> findProductById(Long productId) {
    List<ProductResponse> productResponses = productQueryRepository.searchProductById(productId);
    if (!hasProduct(productResponses)) {
      throw new ProductServiceException(ProductErrorCode.NOT_FOUND_PRODUCT_FOR_ID);
    }
    return productResponses;
  }

  private boolean hasProduct(List<ProductResponse> productResponses) {
    return productResponses != null && !productResponses.isEmpty();
  }

  // TODO: 변환부 MapStruct 사용해보기
  public ProductAddResponse addProduct(ProductRequest request) {
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ProductServiceException(ProductErrorCode.NOT_FOUND_CATEGORY));

    Product product = Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .stock(request.getStock())
        .price(request.getPrice())
        .category(category)
        .build();

    Product saveResp = productRepository.save(product);

    return ProductAddResponse.builder()
        .id(saveResp.getId())
        .build();
  }

  // TODO: 변환부 MapStruct 사용해보기
  public ProductUpdateResponse updateProduct(@Valid ProductRequest request, Long productId) {
    validationCategory(request.getCategoryId());
    validationProduct(productId);

    Product product = Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .stock(request.getStock())
        .price(request.getPrice())
        .build();

    Product saveResponse = productRepository.save(product);

    return ProductUpdateResponse.builder()
        .categoryId(saveResponse.getCategory().getId())
        .id(saveResponse.getId())
        .name(saveResponse.getName())
        .description(saveResponse.getDescription())
        .price(saveResponse.getPrice())
        .stock(saveResponse.getStock())
        .build();
  }

  private void validationCategory(Long categoryId) {
    Integer count = categoryRepository.countById(categoryId);
    if (count == 0) {
      throw new ProductServiceException(ProductErrorCode.NOT_FOUND_CATEGORY);
    }
  }

  private void validationProduct(Long productId) {
    Integer count = productRepository.countById(productId);
    if (count == 0) {
      throw new ProductServiceException(ProductErrorCode.NOT_FOUND_PRODUCT_FOR_ID);
    }
  }

  public ProductDeleteResponse deleteProductProcess(@NotNull Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductServiceException(ProductErrorCode.NOT_FOUND_PRODUCT_FOR_ID));

    Long completePurchaseCount = purchaseQueryRepository.getCompletePurchaseCount(productId);

    if (completePurchaseCount == null || completePurchaseCount > 0) {
      throw new PurchaseServiceException(PurchaseErrorCode.HAS_COMPLETION_PURCHASE);
    }

    productRepository.delete(product);

    return ProductDeleteResponse.builder()
        .productId(productId)
        .build();
  }
}
