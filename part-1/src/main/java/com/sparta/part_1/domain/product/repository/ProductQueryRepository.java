package com.sparta.part_1.domain.product.repository;

import static com.sparta.part_1.domain.category.entity.QCategory.category;
import static com.sparta.part_1.domain.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.part_1.domain.product.dto.request.ProductSearchRequest;
import com.sparta.part_1.domain.product.dto.response.ProductResponse;
import com.sparta.part_1.domain.product.dto.response.QProductResponse;
import com.sparta.part_1.domain.product.entity.QProduct;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  public Page<ProductResponse> searchProductsPage(ProductSearchRequest request,
      Pageable pageable) {
    Long offsetNumber = calcOffSetNumber(request.getPage(), request.getSize());
    List<ProductResponse> productResponses = queryFactory.select(
            new QProductResponse(
                product.id,
                product.name,
                product.price,
                product.stock
            )
        ).from(product)
        .join(category)
        .on(product.category.eq(category), product.category.id.eq(request.getCategory()))
        .where(
            ObjectUtils.isEmpty(request.getCategory()) ? null
                : product.category.id.eq(request.getCategory()),
            ObjectUtils.isEmpty(request.getMaxPrice()) ? null
                : product.price.loe(request.getMaxPrice()),
            ObjectUtils.isEmpty(request.getMinPrice()) ? null
                : product.price.goe(request.getMinPrice())
        )
        .offset(offsetNumber)
        .limit(request.getSize())
        .orderBy(getOrderSpecifier(request.getSortBy(), product))
        .fetch();

    Long totalCount = queryFactory.select(
            product.count()
        ).from(product)
        .join(category)
        .on(product.category.eq(category), product.category.id.eq(request.getCategory()))
        .where(
            ObjectUtils.isEmpty(request.getCategory()) ? null
                : product.category.id.eq(request.getCategory()),
            ObjectUtils.isEmpty(request.getMaxPrice()) ? null
                : product.price.loe(request.getMaxPrice()),
            ObjectUtils.isEmpty(request.getMinPrice()) ? null
                : product.price.goe(request.getMinPrice())
        )
        .offset(offsetNumber)
        .limit(request.getSize())
        .orderBy(getOrderSpecifier(request.getSortBy(), product))
        .fetchOne();

    return new PageImpl<>(productResponses, pageable, totalCount);
  }

  private Long calcOffSetNumber(int page, int size) {
    return Long.valueOf(page * size - page);
  }

  private OrderSpecifier<LocalDateTime> getOrderSpecifier(String sortBy, QProduct qProduct) {
    if (sortBy == null || !sortBy.contains(",")) {
      return qProduct.createdAt.desc(); // 기본 정렬
    }

    String[] parts = sortBy.split(",");
    String fieldName = parts[0];
    String direction = parts[1].toLowerCase();

    Order order = direction.equals("asc") ? Order.ASC : Order.DESC;

    // 동적으로 필드 접근 (주의: 필드명이 정확히 존재해야 함)
    PathBuilder<?> pathBuilder = new PathBuilder<>(qProduct.getType(), qProduct.getMetadata());
    return new OrderSpecifier(order, pathBuilder.get(fieldName));
  }

}
