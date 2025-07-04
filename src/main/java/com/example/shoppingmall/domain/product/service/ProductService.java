package com.example.shoppingmall.domain.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shoppingmall.common.exception.ServiceException;
import com.example.shoppingmall.common.exception.ServiceExceptionCode;
import com.example.shoppingmall.domain.category.entity.Category;
import com.example.shoppingmall.domain.category.repository.CategoryRepository;
import com.example.shoppingmall.domain.product.dto.ProductCreateRequest;
import com.example.shoppingmall.domain.product.dto.ProductCreateResponse;
import com.example.shoppingmall.domain.product.dto.ProductDetailResponse;
import com.example.shoppingmall.domain.product.dto.ProductListResponse;
import com.example.shoppingmall.domain.product.entity.Product;
import com.example.shoppingmall.domain.product.entity.ProductImage;
import com.example.shoppingmall.domain.product.entity.ProductOptionGroup;
import com.example.shoppingmall.domain.product.entity.ProductOptionValue;
import com.example.shoppingmall.domain.product.entity.ProductVariant;
import com.example.shoppingmall.domain.product.entity.ProductVariantOption;
import com.example.shoppingmall.domain.product.mapper.ProductMapper;
import com.example.shoppingmall.domain.product.repository.ProductQueryRepository;
import com.example.shoppingmall.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public Page<ProductListResponse> searchProducts(Long categoryId, Integer minPrice, Integer maxPrice, String sortBy,
            Pageable pageable) {
        return productQueryRepository.searchProducts(categoryId, minPrice, maxPrice, sortBy, pageable);
    }

    public ProductDetailResponse getProductDetail(Long productId) {
        Product product = productQueryRepository.findProductWithAllOptions(productId);
        if (product == null) {
            throw new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND);
        }
        return productMapper.toDetailResponse(product);
    }

    public ProductCreateResponse createProduct(ProductCreateRequest request) {
        // 1. 카테고리 존재 검증 (예시)
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.CATEGORY_NOT_FOUND));

        // 2. 상품명 중복 검증 (예시)
        if (productRepository.existsByName(request.getName())) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_PRODUCT_NAME);
        }

        // 3. 가격/재고 음수 검증
        if (request.getPrice().signum() < 0) {
            throw new ServiceException(ServiceExceptionCode.INVALID_REQUEST);
        }
        if (request.getStock() != null && request.getStock() < 0) {
            throw new ServiceException(ServiceExceptionCode.INVALID_REQUEST);
        }

        // 4. Product 기본 정보 매핑
        Product product = productMapper.fromCreateRequest(request);
        product.setCategory(category); // 카테고리 연관관계 설정

        // 5. 옵션 그룹/옵션 값 생성 및 product에 추가
        if (request.getOptionGroups() != null) {
            for (ProductCreateRequest.OptionGroupRequest ogReq : request.getOptionGroups()) {
                ProductOptionGroup og = new ProductOptionGroup(product, ogReq.getName());
                for (String value : ogReq.getValues()) {
                    ProductOptionValue ov = new ProductOptionValue(og, value);
                    og.getOptionValues().add(ov);
                }
                product.getOptionGroups().add(og);
            }
        }

        // 6. 옵션 조합(variant) 생성 및 product에 추가
        if (request.getVariants() != null) {
            for (ProductCreateRequest.VariantRequest vReq : request.getVariants()) {
                ProductVariant variant = new ProductVariant(product, vReq.getPrice(), vReq.getStock());
                for (int groupIdx = 0; groupIdx < vReq.getOptionValueIndexes().size(); groupIdx++) {
                    int valueIdx = vReq.getOptionValueIndexes().get(groupIdx);
                    ProductOptionGroup group = product.getOptionGroups().get(groupIdx);
                    ProductOptionValue optionValue = group.getOptionValues().get(valueIdx);
                    ProductVariantOption variantOption = new ProductVariantOption(variant, optionValue);
                    variant.getVariantOptions().add(variantOption);
                }
                product.getVariants().add(variant);
            }
        }

        // 7. 이미지 생성 및 product에 추가
        if (request.getImages() != null) {
            for (ProductCreateRequest.ImageRequest imgReq : request.getImages()) {
                ProductImage image = new ProductImage(product, imgReq.getUrl(), imgReq.getType(),
                        imgReq.getSortOrder());
                product.getImages().add(image);
            }
        }

        // 8. 저장 (cascade로 하위 엔티티까지 저장)
        Product savedProduct = productRepository.save(product);

        // 9. 응답 변환
        return productMapper.toCreateResponse(savedProduct);
    }
}
