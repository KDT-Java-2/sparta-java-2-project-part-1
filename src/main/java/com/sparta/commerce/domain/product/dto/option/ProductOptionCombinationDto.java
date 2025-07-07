package com.sparta.commerce.domain.product.dto.option;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class ProductOptionCombinationDto {
  private Long productItemId;        // 옵션 조합별 상품 아이템 ID (재고 관리 단위)
  private Integer stock;             // 재고 수량
  private Long optionGroupId;        // 옵션 그룹 ID (예: 색상)
  private String optionGroupName;    // 옵션 그룹명
  private Long optionItemId;         // 옵션 항목 ID (예: 검정)
  private String optionItemValue;    // 옵션 항목 값
  private BigDecimal additionalPrice; // 옵션별 추가 가격

  @QueryProjection
  public ProductOptionCombinationDto(Long productItemId, Integer stock, Long optionGroupId,
      String optionGroupName, Long optionItemId, String optionItemValue,
      BigDecimal additionalPrice) {
    this.productItemId = productItemId;
    this.stock = stock;
    this.optionGroupId = optionGroupId;
    this.optionGroupName = optionGroupName;
    this.optionItemId = optionItemId;
    this.optionItemValue = optionItemValue;
    this.additionalPrice = additionalPrice;
  }
}
