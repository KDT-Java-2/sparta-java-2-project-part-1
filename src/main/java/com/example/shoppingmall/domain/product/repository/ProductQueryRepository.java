package com.example.shoppingmall.domain.product.repository;

import com.example.shoppingmall.domain.product.dto.ProductListResponse;
import com.example.shoppingmall.domain.product.dto.QProductListResponse;
import com.example.shoppingmall.domain.product.entity.*;
import com.example.shoppingmall.domain.category.entity.QCategory;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<ProductListResponse> searchProducts(
            Long categoryId,
            Integer minPrice,
            Integer maxPrice,
            String sortBy,
            Pageable pageable
    ) {
        QProduct product = QProduct.product;
        QProductVariant variant = QProductVariant.productVariant;
        QProductImage image = QProductImage.productImage;
        QCategory category = QCategory.category;

        // 1. 콘텐츠 쿼리
        List<ProductListResponse> content = queryFactory
                .select(new QProductListResponse(
                        product.id,
                        product.name,
                        product.price,       // 대표 가격
                        variant.stock.sum(), // 총 재고
                        image.url,           // 대표 이미지(썸네일)
                        category.id,
                        category.name,
                        product.createdAt
                ))
                .from(product)
                .leftJoin(product.variants, variant)
                .leftJoin(product.images, image)
                .leftJoin(product.category, category)
                .where(
                        eqCategoryId(categoryId),
                        goeMinPrice(minPrice, variant),
                        loeMaxPrice(maxPrice, variant),
                        image.type.eq("THUMBNAIL").and(image.sortOrder.eq(0))
                )
                .groupBy(product.id, image.url, category.id, category.name)
                .orderBy(getSort(sortBy, product, variant))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2. 카운트 쿼리
        Long total = queryFactory
                .select(product.countDistinct())
                .from(product)
                .leftJoin(product.variants, variant)
                .where(
                        eqCategoryId(categoryId),
                        goeMinPrice(minPrice, variant),
                        loeMaxPrice(maxPrice, variant)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    public Product findProductWithAllOptions(Long productId) {
        QProduct product = QProduct.product;
        QProductVariant variant = QProductVariant.productVariant;

        // 1차 쿼리: Product + variants만 fetch join
        Product result = queryFactory
                .selectFrom(product)
                .leftJoin(product.variants, variant).fetchJoin()
                .where(product.id.eq(productId))
                .fetchOne();

        if (result == null) return null;

        // 2차 쿼리: 모든 variant id 수집
        List<Long> variantIds = result.getVariants().stream()
                .map(ProductVariant::getId)
                .toList();

        if (!variantIds.isEmpty()) {
            QProductVariantOption variantOption = QProductVariantOption.productVariantOption;
            QProductOptionValue optionValue = QProductOptionValue.productOptionValue;
            QProductOptionGroup optionGroup = QProductOptionGroup.productOptionGroup;

            // 2차 쿼리: variantOptions + optionValue + optionGroup을 IN절로 한 번에 조회
            List<ProductVariantOption> allOptions = queryFactory
                    .selectFrom(variantOption)
                    .leftJoin(variantOption.optionValue, optionValue).fetchJoin()
                    .leftJoin(optionValue.optionGroup, optionGroup).fetchJoin()
                    .where(variantOption.productVariant.id.in(variantIds))
                    .fetch();

            // 조립: variantId별로 옵션값을 매핑
            Map<Long, List<ProductVariantOption>> optionMap = allOptions.stream()
                    .collect(Collectors.groupingBy(vo -> vo.getProductVariant().getId()));

            for (ProductVariant variantEntity : result.getVariants()) {
                List<ProductVariantOption> options = optionMap.get(variantEntity.getId());
                if (options != null) {
                    variantEntity.getVariantOptions().clear();
                    variantEntity.getVariantOptions().addAll(options);
                }
            }
        }

        return result;
    }

    private BooleanExpression eqCategoryId(Long categoryId) {
        return categoryId != null ? QProduct.product.category.id.eq(categoryId) : null;
    }

    private BooleanExpression goeMinPrice(Integer minPrice, QProductVariant variant) {
        return minPrice != null ? variant.price.goe(minPrice) : null;
    }

    private BooleanExpression loeMaxPrice(Integer maxPrice, QProductVariant variant) {
        return maxPrice != null ? variant.price.loe(maxPrice) : null;
    }

    private OrderSpecifier<?> getSort(String sortBy, QProduct product, QProductVariant variant) {
        if (sortBy == null || sortBy.isBlank()) {
            return product.createdAt.desc();
        }
        String[] parts = sortBy.split(",");
        String field = parts[0];
        boolean asc = parts.length > 1 && "asc".equalsIgnoreCase(parts[1]);
        switch (field) {
            case "price":
                return asc ? variant.price.asc() : variant.price.desc();
            case "createdAt":
            default:
                return asc ? product.createdAt.asc() : product.createdAt.desc();
        }
    }


}