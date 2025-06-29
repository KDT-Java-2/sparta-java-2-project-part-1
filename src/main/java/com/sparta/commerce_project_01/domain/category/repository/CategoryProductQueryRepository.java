package com.sparta.commerce_project_01.domain.category.repository;

import static com.sparta.commerce_project_01.domain.category.entity.QCategory.category;
import static com.sparta.commerce_project_01.domain.product.entity.QProduct.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce_project_01.domain.category.dto.CategoryProductDTO;
import com.sparta.commerce_project_01.domain.category.dto.QCategoryProductDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  public List<CategoryProductDTO> findCategoryProducts(String categoryName) {
    return queryFactory
        .select(new QCategoryProductDTO(
            category.name,
            product.name,
            product.price,
            product.stock
        ))
        .from(product)
        .join(product.category, category)
        .where(category.name.eq(categoryName))
        .fetch();
  }

}
