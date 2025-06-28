package com.sparta.spartajava2projectpart1.domain.purchase.controller;

import com.sparta.spartajava2projectpart1.common.response.ApiResponse;
import com.sparta.spartajava2projectpart1.domain.purchase.entity.Purchase;
import com.sparta.spartajava2projectpart1.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public ApiResponse<List<Purchase>> getAllPurchases(@RequestParam Long userId) {
        return ApiResponse.success(purchaseService.getAllPurchases(userId));
    }
}
