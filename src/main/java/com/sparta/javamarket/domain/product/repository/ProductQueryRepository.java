package com.sparta.javamarket.domain.product.repository;

import static  com.sparta.javamarket.domain.product.entity.QProduct.product;

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

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;
  private final ProductMapper productMapper;

  public Page<ProductResponse> findAllProducts(ProductSearchRequest productSearchRequest) {

    Pageable pageable = PageRequest.of(productSearchRequest.getPage(), productSearchRequest.getSize());

    List<ProductResponse> products =  jpaQueryFactory.select(new QProductResponse(
        product.id,
        product.name,
        product.price,
        product.stock
        ))
        .from(product)
        .where()
        .orderBy(product.createdAt.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long totalCount = jpaQueryFactory.select(product.count())
        .from(product)
        .where()
        .fetchOne();

    return new PageImpl<>(products, pageable, totalCount);

  }

}
