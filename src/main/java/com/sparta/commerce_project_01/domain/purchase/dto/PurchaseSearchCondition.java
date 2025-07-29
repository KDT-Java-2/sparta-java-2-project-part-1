package com.sparta.commerce_project_01.domain.purchase.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseSearchCondition {

  Long userId;
  String status;
  int limit;
  int offset;
}
