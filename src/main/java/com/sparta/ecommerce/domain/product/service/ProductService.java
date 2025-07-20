package com.sparta.ecommerce.domain.product.service;

import com.sparta.ecommerce.common.exception.ServiceException;
import com.sparta.ecommerce.common.exception.ServiceExceptionCode;
import com.sparta.ecommerce.common.response.PageResponse;
import com.sparta.ecommerce.domain.category.entity.Category;
import com.sparta.ecommerce.domain.category.repository.CategoryRepository;
import com.sparta.ecommerce.domain.product.dto.ProductCreateRequest;
import com.sparta.ecommerce.domain.product.dto.ProductCreateResponse;
import com.sparta.ecommerce.domain.product.dto.ProductInfoResponse;
import com.sparta.ecommerce.domain.product.dto.ProductListResponse;
import com.sparta.ecommerce.domain.product.dto.ProductSearchRequest;
import com.sparta.ecommerce.domain.product.entity.Product;
import com.sparta.ecommerce.domain.product.mapper.ProductMapper;
import com.sparta.ecommerce.domain.product.repository.ProductQueryRepository;
import com.sparta.ecommerce.domain.product.repository.ProductRepository;
import com.sparta.ecommerce.domain.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final PurchaseRepository purchaseRepository;
  private final ProductQueryRepository productQueryRepository;

  public PageResponse<ProductListResponse> getProductPageList(ProductSearchRequest request,
      Pageable pageable) {

    Page<ProductListResponse> page = productQueryRepository.findProductList(request, pageable);
    return PageResponse.from(page);
  }


  public ProductInfoResponse getById(Long id) {
    Product product = productRepository.findWithCategoryById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    return productMapper.toProductResponse(product);
  }

  @Transactional
  public ProductCreateResponse save(ProductCreateRequest request) {
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    if (productRepository.findByProductNm(request.getName()).isPresent()) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_PROUDCT_NAME);
    }

    Product product = productMapper.toEntity(request, category);

    Product result = productRepository.save(product);

    return productMapper.toProductCreateResponse(result);
  }

  @Transactional
  public ProductInfoResponse update(Long id, ProductCreateRequest request) {
    Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    if (!product.getProductNm().equals(request.getName())
        && productRepository.findByProductNm(request.getName()).isPresent()) {
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_PROUDCT_NAME);
    }

    product.update(request, category);

    return productMapper.toProductResponse(product);
  }

  @Transactional
  public void delete(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    if (purchaseRepository.existsProductInCompletedPurchase(product.getId())) {
      throw new ServiceException(ServiceExceptionCode.EXISTS_ORDERED_PRODUCT);
    }

    productRepository.delete(product);
  }
}
