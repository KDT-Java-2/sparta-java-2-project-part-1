package me.chahyunho.projectweek1.domain.purchase.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.common.enums.response.ApiResponse;
import me.chahyunho.projectweek1.domain.purchase.dto.PurchaseRequest;
import me.chahyunho.projectweek1.domain.purchase.service.PurchaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchases")
public class PurchaseController {

  private final PurchaseService purchaseService;

  @PostMapping
  public ApiResponse<Void> placePurchase(@Valid @RequestBody PurchaseRequest request) {
    purchaseService.placePurchase(request);
    return ApiResponse.success();
  }

}
