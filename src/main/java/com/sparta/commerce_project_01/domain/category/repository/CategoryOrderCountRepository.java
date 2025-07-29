package com.sparta.commerce_project_01.domain.category.repository;

import static com.sparta.commerce_project_01.domain.product.entity.QProduct.product;
import static com.sparta.commerce_project_01.domain.purchase.entity.QPurchase.purchase;
import static com.sparta.commerce_project_01.domain.purchase.entity.QPurchaseProduct.purchaseProduct;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce_project_01.domain.category.dto.CategoryOrderCountDTO;
import com.sparta.commerce_project_01.domain.category.dto.QCategoryOrderCountDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryOrderCountRepository {

  private final JPAQueryFactory queryFactory;

  public List<CategoryOrderCountDTO> findCategoryOrderCounts() {
    return queryFactory
        .select(new QCategoryOrderCountDTO(
            product.category.name,
            purchase.id.countDistinct()
        ))
        .from(purchaseProduct)
        .join(purchaseProduct.purchase, purchase)
        .join(purchaseProduct.product, product)
        .groupBy(product.category.name)
        .orderBy(purchase.id.countDistinct().desc())
        .fetch();
  }

}
