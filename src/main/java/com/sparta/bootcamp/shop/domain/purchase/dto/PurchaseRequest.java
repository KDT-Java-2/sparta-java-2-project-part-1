package com.sparta.bootcamp.shop.domain.purchase.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequest {

    @NotNull
    Long userId;

    @NotNull
    List<PurchaseProductRequest> products;

}