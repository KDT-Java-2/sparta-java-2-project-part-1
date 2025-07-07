package com.sparta.commerce.domain.product.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.sparta.commerce.domain.product.entity.QProduct;
import com.sparta.commerce.domain.product.entity.QProductItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductOrder {
  /**
   * description은 프론트와 협의
   */
  LATEST { // 최신순
    @Override
    public OrderSpecifier<?> getOrderSpecifier() {
      return QProduct.product.createdAt.desc();
    }
  },
  PRICE_ASC { // 가격이 낮은 순
    @Override
    public OrderSpecifier<?> getOrderSpecifier() {
      return QProduct.product.basePrice.asc();
    }
  },
  PRICE_DESC { //가격 높은 순
    @Override
    public OrderSpecifier<?> getOrderSpecifier() {
      return QProduct.product.basePrice.desc();
    }
  },
  // 정렬 조건 추가 예정
  ;

  public abstract OrderSpecifier<?> getOrderSpecifier();
}
