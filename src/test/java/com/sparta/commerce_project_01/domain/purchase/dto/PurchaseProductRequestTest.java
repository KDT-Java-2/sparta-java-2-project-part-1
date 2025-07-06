package com.sparta.commerce_project_01.domain.purchase.dto;

public class PurchaseProductRequestTest {

  private final Long productId;

  private final Integer quantity;

  public PurchaseProductRequestTest(Long productId, Integer quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public Integer getQuantity() {
    return quantity;
  }
}