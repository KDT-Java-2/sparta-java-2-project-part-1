package com.scb.project.commerce.domain.product.repository;

import static com.scb.project.commerce.domain.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scb.project.commerce.domain.product.dto.ProductSearchRequest;
import com.scb.project.commerce.domain.product.dto.ProductSearchResponse;
import com.scb.project.commerce.domain.product.dto.QProductSearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * 상품 검색 조건과 페이징 정보를 바탕으로 상품 목록을 조회합니다.
     * <p>QueryDSL을 사용하여 카테고리, 가격 조건, 정렬 기준 등을 적용하며, 페이징 처리를 수행합니다.</p>
     *
     * @param request  검색 조건이 포함된 요청 객체
     * @param pageable 페이지 및 정렬 정보
     * @return 조건에 맞는 상품 목록과 페이징 정보를 담은 Page 객체
     */
    public Page<ProductSearchResponse> findPagedProducts(
        ProductSearchRequest request,
        Pageable pageable
    ) {
        List<ProductSearchResponse> responses = queryFactory
            .select(new QProductSearchResponse(
                product.id,
                product.productName,
                product.price,
                product.stock
            ))
            .from(product)
            .where(
                categoryEq(request.getCategory()),
                minPriceGoe(request.getMinPrice()),
                maxPriceLoe(request.getMaxPrice())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(sortOrder(request.getSortBy()))
            .fetch();

        Long totalSize = queryFactory
            .select(product.count())
            .from(product)
            .where(
                categoryEq(request.getCategory()),
                minPriceGoe(request.getMinPrice()),
                maxPriceLoe(request.getMaxPrice())
            )
            .fetchOne();

        return new PageImpl<>(responses, pageable, totalSize);
    }

    // 카테고리 ID가 주어진 경우 해당 카테고리 조건 생성
    private BooleanExpression categoryEq(Long categoryId) {
        return categoryId != null ? product.category.id.eq(categoryId) : null;
    }

    // 최소 가격 조건 생성
    private BooleanExpression minPriceGoe(Integer minPrice) {
        return minPrice != null ? product.price.goe(minPrice) : null;
    }

    // 최대 가격 조건 생성
    private BooleanExpression maxPriceLoe(Integer maxPrice) {
        return maxPrice != null ? product.price.loe(maxPrice) : null;
    }

    // 정렬 기준에 따라 OrderSpecifier 반환
    private OrderSpecifier<?> sortOrder(String sortBy) {

        if (!StringUtils.hasText(sortBy)) {
            return new OrderSpecifier<>(Order.DESC, product.createdAt);
        }

        String[] parts = sortBy.split(",");
        String field = parts[0];
        Order direction =
            (parts.length == 2 && parts[1].equalsIgnoreCase("asc"))
                ? Order.ASC : Order.DESC;

        return (switch (field) {
            case "id" -> new OrderSpecifier<>(direction, product.id);
            case "productName" -> new OrderSpecifier<>(direction, product.productName);
            case "price" -> new OrderSpecifier<>(direction, product.price);
            case "stock" -> new OrderSpecifier<>(direction, product.stock);
            default -> new OrderSpecifier<>(direction, product.createdAt);
        });
    }
}
