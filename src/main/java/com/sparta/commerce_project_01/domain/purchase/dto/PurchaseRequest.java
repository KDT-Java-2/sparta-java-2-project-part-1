package com.sparta.commerce_project_01.domain.purchase.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.wildfly.common.annotation.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequest {

  @NotNull
  Long userId;

  @NotNull
  List<PurchaseProductRequest> products;
}
