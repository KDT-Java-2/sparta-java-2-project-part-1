package com.sparta.e_project.domain.product.repository;


import static com.sparta.e_project.domain.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.e_project.domain.product.dto.ProductRequest;
import com.sparta.e_project.domain.product.dto.ProductResponse;
import com.sparta.e_project.domain.product.dto.QProductResponse;
import com.sparta.e_project.domain.product.entity.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public Page<ProductResponse> findPagedProducts(ProductRequest request, Pageable pageable) {
    List<ProductResponse> productResponses = jpaQueryFactory
        .select(new QProductResponse(
            product.id,
            product.name,
            product.price,
            product.stock))
        .from(product)
        .where(
            categoryEq(request.getCategory()),
            priceGoe(request.getMinPrice()),
            priceLoe(request.getMaxPrice())
        )
        // order by에는 필드명,정렬 방향이 올수 있다.
        .orderBy(createOrderSpecifier(request.getSortBy()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    long total = jpaQueryFactory.select(product.count())
        .from(product)
        .where(
            categoryEq(request.getCategory()),
            priceGoe(request.getMinPrice()),
            priceLoe(request.getMaxPrice())
        )
        .fetchOne();

    return new PageImpl<>(productResponses, pageable, total);
  }

  private BooleanExpression categoryEq(Long categoryId) {
    return categoryId != null ? product.category.id.eq(categoryId) : null;
  }

  private BooleanExpression priceGoe(Integer minPrice) {
    return minPrice != null ? product.price.goe(minPrice) : null;
  }

  private BooleanExpression priceLoe(Integer maxPrice) {
    return maxPrice != null ? product.price.loe(maxPrice) : null;
  }

  private OrderSpecifier<?> createOrderSpecifier(String sortBy) {
    // 1. 문자열 파싱: "price,desc" -> ["price", "desc"]
    String[] parts = sortBy.split(",");
    String field = parts[0];  // "price"

    // 2. 정렬 방향 결정: desc면 DESC, 아니면 ASC
    Order order = parts.length > 1 && "desc".equalsIgnoreCase(parts[1])
        ? Order.DESC : Order.ASC;

    // 3. PathBuilder 생성: Product 엔티티의 필드에 동적 접근
    PathBuilder<Product> pathBuilder = new PathBuilder<>(Product.class, "product");

    // 4. OrderSpecifier 생성: 정렬 조건 완성
    return new OrderSpecifier<>(order, pathBuilder.getComparable(field, Comparable.class));
  }
}
