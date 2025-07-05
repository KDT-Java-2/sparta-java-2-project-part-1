package com.example.shoppingmall.domain.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shoppingmall.common.exception.ServiceException;
import com.example.shoppingmall.common.exception.ServiceExceptionCode;
import com.example.shoppingmall.domain.category.entity.Category;
import com.example.shoppingmall.domain.category.repository.CategoryRepository;
import com.example.shoppingmall.domain.product.dto.ProductCreateRequest;
import com.example.shoppingmall.domain.product.dto.ProductCreateResponse;
import com.example.shoppingmall.domain.product.dto.ProductDetailResponse;
import com.example.shoppingmall.domain.product.dto.ProductListResponse;
import com.example.shoppingmall.domain.product.dto.ProductUpdateRequest;
import com.example.shoppingmall.domain.product.dto.ProductUpdateResponse;
import com.example.shoppingmall.domain.product.dto.ProductDeleteResponse;
import com.example.shoppingmall.domain.product.entity.Product;
import com.example.shoppingmall.domain.product.entity.ProductImage;
import com.example.shoppingmall.domain.product.entity.ProductOptionGroup;
import com.example.shoppingmall.domain.product.entity.ProductOptionValue;
import com.example.shoppingmall.domain.product.entity.ProductVariant;
import com.example.shoppingmall.domain.product.entity.ProductVariantOption;
import com.example.shoppingmall.domain.product.mapper.ProductMapper;
import com.example.shoppingmall.domain.product.repository.ProductQueryRepository;
import com.example.shoppingmall.domain.product.repository.ProductRepository;
import com.example.shoppingmall.domain.purchase.repository.PurchaseProductRepository;
import com.example.shoppingmall.common.enums.PurchaseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final PurchaseProductRepository purchaseProductRepository;

    @Transactional
    public Page<ProductListResponse> searchProducts(Long categoryId, Integer minPrice, Integer maxPrice, String sortBy,
            Pageable pageable) {
        return productQueryRepository.searchProducts(categoryId, minPrice, maxPrice, sortBy, pageable);
    }

    @Transactional
    public ProductDetailResponse getProductDetail(Long productId) {
        Product product = productQueryRepository.findProductWithAllOptions(productId);
        if (product == null) {
            throw new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND);
        }
        return productMapper.toDetailResponse(product);
    }

    @Transactional
    public ProductCreateResponse createProduct(ProductCreateRequest request) {
        // 1. 카테고리 검증
        Category category = validateCategory(request.getCategoryId());

        // 2. 상품명 중복 검증
        validateProductNameForCreate(request.getName());

        // 3. 가격/재고 검증
        validatePriceAndStock(request.getPrice(), request.getStock());

        // 4. Product 기본 정보 매핑
        Product product = productMapper.fromCreateRequest(request);
        product.setCategory(category);

        // 5. 옵션 그룹/값, variant, 이미지 추가
        addOptionGroupsToProduct(product, request.getOptionGroups());
        addVariantsToProduct(product, request.getVariants());
        addImagesToProductFromCreate(product, request.getImages());

        // 6. 저장 및 응답 변환
        Product savedProduct = productRepository.save(product);
        return productMapper.toCreateResponse(savedProduct);
    }

    @Transactional
    public ProductUpdateResponse updateProduct(Long productId, ProductUpdateRequest request) {
        // 1. 기존 상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND));

        // 2. 카테고리 검증
        Category category = validateCategory(request.getCategoryId());

        // 3. 상품명 중복 검증 (본인 제외)
        validateProductNameForUpdate(request.getName(), productId);

        // 4. 가격/재고 검증
        validatePriceAndStock(request.getPrice(), request.getStock());

        // 5. 상품 기본 정보 업데이트
        product.updateBasicInfo(request.getName(), request.getDescription(), request.getPrice());
        product.setCategory(category);

        // 6. 옵션 그룹/값 부분 업데이트 (기존 + 새로운)
        updateOptionGroupsPartially(product, request.getOptionGroups());
        
        // 7. variant 부분 업데이트 (기존 + 새로운)
        updateVariantsPartially(product, request.getVariants());
        
        // 8. 이미지 부분 업데이트 (기존 + 새로운)
        updateImagesPartially(product, request.getImages());

        // 8. 저장 및 응답 변환
        Product savedProduct = productRepository.save(product);
        return productMapper.toUpdateResponse(savedProduct);
    }

    @Transactional
    public ProductDeleteResponse deleteProduct(Long productId) {
        // 1. 상품 존재 검증
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND));

        // 2. 완료된 주문에 포함되어 있는지 검증
        boolean hasCompletedOrders = purchaseProductRepository
                .existsByProductIdAndPurchaseStatus(productId, PurchaseStatus.COMPLETED);
        
        if (hasCompletedOrders) {
            throw new ServiceException(ServiceExceptionCode.PRODUCT_CANNOT_DELETE_HAS_COMPLETED_ORDERS);
        }

        // 3. 상품 삭제 (cascade로 관련 엔티티들도 삭제됨)
        productRepository.delete(product);

        // 4. 응답 생성
        return new ProductDeleteResponse(true, productId);
    }

    // === 공통 유틸리티 메서드들 ===
    
    private Category validateCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));
    }

    private void validateProductNameForCreate(String name) {
        if (productRepository.existsByName(name)) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
        }
    }

    private void validateProductNameForUpdate(String name, Long productId) {
        if (productRepository.existsByNameAndIdNot(name, productId)) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
        }
    }

    private void validatePriceAndStock(java.math.BigDecimal price, Integer stock) {
        if (price.signum() < 0) {
            throw new ServiceException(ServiceExceptionCode.INVALID_REQUEST);
        }
        if (stock != null && stock < 0) {
            throw new ServiceException(ServiceExceptionCode.INVALID_REQUEST);
        }
    }

    private void addOptionGroupsToProduct(Product product, java.util.List<ProductCreateRequest.OptionGroupRequest> optionGroups) {
        if (optionGroups != null) {
            for (ProductCreateRequest.OptionGroupRequest ogReq : optionGroups) {
                createOptionGroup(product, ogReq.getName(), ogReq.getValues());
            }
        }
    }

    private void addVariantsToProduct(Product product, java.util.List<ProductCreateRequest.VariantRequest> variants) {
        if (variants != null) {
            for (ProductCreateRequest.VariantRequest vReq : variants) {
                createVariant(product, vReq.getPrice(), vReq.getStock(), vReq.getOptionValues());
            }
        }
    }

    private void addImagesToProductFromCreate(Product product, java.util.List<ProductCreateRequest.ImageRequest> images) {
        if (images != null) {
            for (ProductCreateRequest.ImageRequest imgReq : images) {
                createImage(product, imgReq.getUrl(), imgReq.getType(), imgReq.getSortOrder());
            }
        }
    }

    // === 부분 업데이트 메서드들 ===

    private void updateOptionGroupsPartially(Product product, java.util.List<ProductUpdateRequest.OptionGroupRequest> optionGroups) {
        if (optionGroups != null) {
            for (ProductUpdateRequest.OptionGroupRequest ogReq : optionGroups) {
                // 기존 옵션 그룹 찾기
                ProductOptionGroup existingGroup = product.getOptionGroups().stream()
                    .filter(og -> og.getName().equals(ogReq.getName()))
                    .findFirst()
                    .orElse(null);
                
                if (existingGroup == null) {
                    // 새로운 옵션 그룹 생성
                    createOptionGroup(product, ogReq.getName(), ogReq.getValues());
                } else {
                    // 기존 그룹에 새로운 옵션 값들 추가
                    for (String value : ogReq.getValues()) {
                        if (!existsOptionValue(existingGroup, value)) {
                            ProductOptionValue ov = new ProductOptionValue(existingGroup, value);
                            existingGroup.getOptionValues().add(ov);
                        }
                    }
                }
            }
        }
    }

    private void updateVariantsPartially(Product product, java.util.List<ProductUpdateRequest.VariantRequest> variants) {
        if (variants != null) {
            for (ProductUpdateRequest.VariantRequest vReq : variants) {
                // 중복 variant 검사 (같은 옵션 조합이 이미 존재하는지)
                if (!existsVariant(product, vReq.getOptionValues())) {
                    // 새로운 variant 추가
                    createVariant(product, vReq.getPrice(), vReq.getStock(), vReq.getOptionValues());
                }
            }
        }
    }

    private void updateImagesPartially(Product product, java.util.List<ProductUpdateRequest.ImageRequest> images) {
        if (images != null) {
            for (ProductUpdateRequest.ImageRequest imgReq : images) {
                // 중복 이미지 검사 (같은 URL이 이미 존재하는지)
                if (!existsImage(product, imgReq.getUrl())) {
                    createImage(product, imgReq.getUrl(), imgReq.getType(), imgReq.getSortOrder());
                }
            }
        }
    }

    private boolean hasSameOptionCombination(ProductVariant variant, java.util.List<String> optionValues) {
        if (variant.getVariantOptions().size() != optionValues.size()) {
            return false;
        }
        
        for (int i = 0; i < optionValues.size(); i++) {
            String targetValue = optionValues.get(i);
            boolean found = variant.getVariantOptions().stream()
                .anyMatch(vo -> vo.getOptionValue().getValue().equals(targetValue));
            if (!found) {
                return false;
            }
        }
        return true;
    }

    // === 핵심 생성 메서드들 (공통화) ===

    private void createOptionGroup(Product product, String name, java.util.List<String> values) {
        ProductOptionGroup og = new ProductOptionGroup(product, name);
        for (String value : values) {
            ProductOptionValue ov = new ProductOptionValue(og, value);
            og.getOptionValues().add(ov);
        }
        product.getOptionGroups().add(og);
    }

    private void createVariant(Product product, java.math.BigDecimal price, Integer stock, java.util.List<String> optionValues) {
        ProductVariant variant = new ProductVariant(product, price, stock);
        for (int groupIdx = 0; groupIdx < optionValues.size(); groupIdx++) {
            String optionValueName = optionValues.get(groupIdx);
            ProductOptionGroup group = product.getOptionGroups().get(groupIdx);
            ProductOptionValue optionValue = group.getOptionValues().stream()
                .filter(ov -> ov.getValue().equals(optionValueName))
                .findFirst()
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.INVALID_OPTION_VALUE));
            ProductVariantOption variantOption = new ProductVariantOption(variant, optionValue);
            variant.getVariantOptions().add(variantOption);
        }
        product.getVariants().add(variant);
    }

    private void createImage(Product product, String url, String type, Integer sortOrder) {
        ProductImage image = new ProductImage(product, url, type, sortOrder);
        product.getImages().add(image);
    }

    // === 중복 검사 메서드들 ===

    private boolean existsOptionValue(ProductOptionGroup group, String value) {
        return group.getOptionValues().stream()
            .anyMatch(ov -> ov.getValue().equals(value));
    }

    private boolean existsVariant(Product product, java.util.List<String> optionValues) {
        return product.getVariants().stream()
            .anyMatch(variant -> hasSameOptionCombination(variant, optionValues));
    }

    private boolean existsImage(Product product, String url) {
        return product.getImages().stream()
            .anyMatch(img -> img.getUrl().equals(url));
    }
}
