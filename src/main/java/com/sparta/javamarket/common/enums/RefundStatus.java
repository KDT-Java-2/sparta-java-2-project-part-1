package com.sparta.javamarket.common.enums;

public enum RefundStatus {

  REQUESTED,     // 고객이 환불을 요청한 상태
  APPROVED,      // 관리자 또는 시스템이 환불을 승인한 상태
  REJECTED,      // 환불이 거절된 상태
  COMPLETED

}
