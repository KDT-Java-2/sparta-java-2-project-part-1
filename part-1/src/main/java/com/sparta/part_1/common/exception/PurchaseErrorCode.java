package com.sparta.part_1.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum PurchaseErrorCode {
  HAS_COMPLETION_PURCHASE("완료상태인 주문이 있습니다! \n해당 건이 마무리되면 삭제해주세요.");

  final String message;
}
