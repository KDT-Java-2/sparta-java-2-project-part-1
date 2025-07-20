package com.sparta.commerce.domain.product.service;

import com.sparta.commerce.common.response.PagingResponse;
import com.sparta.commerce.domain.category.entity.Category;
import com.sparta.commerce.domain.product.dto.image.ProductImageResponse;
import com.sparta.commerce.domain.product.dto.option.ProductOptionResponse;
import com.sparta.commerce.domain.product.dto.product.ProductDetailResponse;
import com.sparta.commerce.domain.product.dto.product.ProductRequest;
import com.sparta.commerce.domain.product.dto.product.ProductSearchCondition;
import com.sparta.commerce.domain.product.dto.product.ProductSummaryResponse;
import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.repository.ProductRepository;
import com.sparta.commerce.domain.product.repository.queryDsl.ProductOptionQueryRepository;
import com.sparta.commerce.domain.product.repository.queryDsl.ProductQueryRepository;
import com.sparta.commerce.domain.product.validate.ProductValidator;
import com.sparta.commerce.domain.seller.entity.Seller;
import com.sparta.commerce.domain.seller.validate.SellerValidator;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductValidator productValidator;
  private final ProductImageService productImageService;
  private final OptionService optionService;
  private final ProductRepository productRepository;

  private final SellerValidator sellerValidator;

  private final ProductQueryRepository productQueryRepository;
  private final ProductOptionQueryRepository productOptionQueryRepository;


  @Transactional
  public Long createProduct(ProductRequest request, List<MultipartFile> files){
    Category category = productValidator.validateAndGetCategory(request.getCategoryId());
    productValidator.validatePrice(request.getBasePrice());

    //TODO  Spring Security 인증 기능 도입 전까지 임시로 사용
    Seller seller = sellerValidator.getSeller(1L);

    Product product = createProduct(seller, category, request);
    productRepository.save(product);
    productImageService.saveProductImages(product, request.getImages(), files);
    optionService.saveOptions(product, request.getOptionGroups(), request.getProductItems());

    return product.getId();
  }

  @Transactional
  public ProductDetailResponse updateProduct(Long productId, ProductRequest request, List<MultipartFile> files) {
    Product product = productValidator.getProduct(productId);
    updateProductBaseInfo(product, request);
    List<ProductImageResponse> productImageResponses = productImageService.updateProductImages(product, request.getImages(), files);
    optionService.deleteOptions(product);
    List<ProductOptionResponse> options = optionService.saveOptions(product, request.getOptionGroups(), request.getProductItems());
    return ProductDetailResponse.of(product, productImageResponses, options);
  }

  @Transactional
  public void deleteProduct(Long productId){
    Product product = productValidator.getProduct(productId);
    productValidator.validateDeletable(product);

    optionService.deleteOptions(product);
    productImageService.delete(product);
    productRepository.delete(product);
  }

  @Transactional(readOnly = true)
  public ProductDetailResponse getProductItem(Long productId) {
    Product product = productValidator.getProduct(productId);
    List<ProductImageResponse> images = productImageService.getImages(product);
    List<ProductOptionResponse> optionCombination = productOptionQueryRepository.findOptionCombination(product);
    return ProductDetailResponse.of(product, images, optionCombination);
  }

  @Transactional(readOnly = true)
  public PagingResponse<ProductSummaryResponse> searchProducts(ProductSearchCondition searchCondition, Pageable pageable){
    Page<Product> page = productQueryRepository.searchProducts(searchCondition, pageable);
    Map<Long, ProductImageResponse> thumbnailImages = productImageService.getThumbnailImages(page.getContent());
    List<ProductSummaryResponse> summaryResponses = page.stream()
        .map(product -> toProductSummaryResponse(product, thumbnailImages.get(product.getId())))
        .toList();
    return PagingResponse.of(summaryResponses, page);
  }

  private void updateProductBaseInfo(Product product, ProductRequest request) {
    product.update(
        request.getName(),
        request.getDescription(),
        request.getBrand(),
        request.getBasePrice()
    );
  }

  private ProductSummaryResponse toProductSummaryResponse(Product product, ProductImageResponse imageResponse){
    return ProductSummaryResponse.builder()
        .productId(product.getId())
        .name(product.getName())
        .brand(product.getBrand())
        .thumbnail(imageResponse)
        .basePrice(product.getBasePrice())
        .build();
  }

  private Product createProduct(Seller seller, Category category, ProductRequest request) {
    return Product.builder()
        .seller(seller)
        .name(request.getName())
        .description(request.getDescription())
        .brand(request.getBrand())
        .basePrice(request.getBasePrice())
        .category(category)
        .build();
  }
}
