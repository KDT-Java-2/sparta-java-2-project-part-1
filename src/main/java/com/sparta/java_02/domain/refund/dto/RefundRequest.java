package com.sparta.java_02.domain.refund.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RefundRequest {

  @NotNull(message = "구매 ID는 필수입니다.")
  private Long purchaseId;

  @NotBlank(message = "환불 사유는 필수입니다.")
  private String reasonCd;

}