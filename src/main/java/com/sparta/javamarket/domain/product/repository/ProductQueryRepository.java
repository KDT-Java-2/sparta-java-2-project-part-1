package com.sparta.javamarket.domain.product.repository;

import static  com.sparta.javamarket.domain.product.entity.QProduct.product;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.javamarket.domain.product.dto.ProductResponse;
import com.sparta.javamarket.domain.product.dto.ProductSearchRequest;
import com.sparta.javamarket.domain.product.dto.ProductSearchResponse;
import com.sparta.javamarket.domain.product.dto.QProductResponse;
import com.sparta.javamarket.domain.product.entity.Product;
import com.sparta.javamarket.domain.product.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;
  private final ProductMapper productMapper;

  public Page<ProductResponse> findAllProducts(ProductSearchRequest productSearchRequest) {

    System.out.println("Finding all products for search request");
    Pageable pageable = PageRequest.of(productSearchRequest.getPage(), productSearchRequest.getSize());

    Integer minPrice = productSearchRequest.getMinPrice();
    Integer maxPrice = productSearchRequest.getMaxPrice();
    String sortBy = productSearchRequest.getSortBy();
    Long category = productSearchRequest.getCategory();

    List<ProductResponse> products =  jpaQueryFactory.select(new QProductResponse(
        product.id,
        product.name,
        product.price,
        product.stock
        ))
        .from(product)
        .where(
            categoryContains(category),
            priceGoe(minPrice),
            priceLoe(maxPrice)
        )
        .orderBy(getOrderSpecifier(sortBy))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long totalCount = jpaQueryFactory.select(product.count())
        .from(product)
        .where()
        .fetchOne();

    System.out.println("Found " + totalCount + " products for search request");

    return new PageImpl<>(products, pageable, totalCount);

  }

  private OrderSpecifier<?> getOrderSpecifier(String sortBy) {
    String sortField = "createdAt"; // 기본값
    Order direction = Order.DESC;

    if (StringUtils.hasText(sortBy) && sortBy.contains(",")) {
      String[] parts = sortBy.split(",");
      if (parts.length == 2) {
        sortField = parts[0];
        direction = parts[1].equalsIgnoreCase("asc") ? Order.ASC : Order.DESC;
      }
    }

    PathBuilder<Product> pathBuilder = new PathBuilder<>(Product.class, "product");
    return new OrderSpecifier<>(direction, pathBuilder.getString(sortField));
  }

  private BooleanExpression categoryContains(Long category) {
    return StringUtils.hasText(category.toString()) ? product.categoryId.eq(category) : null;
  }

  private BooleanExpression priceGoe(Integer minPrice){
    return minPrice != null ? product.price.goe(minPrice) : null;
  }

  private BooleanExpression priceLoe(Integer maxPrice){
    return maxPrice != null ? product.price.loe(maxPrice) : null;
  }


}
