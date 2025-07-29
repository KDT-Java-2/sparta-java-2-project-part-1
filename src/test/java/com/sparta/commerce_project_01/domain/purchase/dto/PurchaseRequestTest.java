package com.sparta.commerce_project_01.domain.purchase.dto;

import java.util.List;


public class PurchaseRequestTest {

  private final Long userId;

  private final List<PurchaseProductRequestTest> products;

  public PurchaseRequestTest(Long userId, List<PurchaseProductRequestTest> products) {
    this.userId = userId;
    this.products = products;
  }

  public Long getUserId() {
    return userId;
  }

  public List<PurchaseProductRequestTest> getProducts() {
    return products;
  }
}