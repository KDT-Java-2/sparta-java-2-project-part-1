package com.sparta.commerce.domain.product.repository.queryDsl;

import static com.sparta.commerce.domain.product.entity.QOptionGroup.optionGroup;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OptionGroupQueryRepository {

  private final JPAQueryFactory queryFactory;

  public void deleteByProduct(Product product) {
    queryFactory.delete(optionGroup)
        .where(optionGroup.product.eq(product))
        .execute();
  }

}
