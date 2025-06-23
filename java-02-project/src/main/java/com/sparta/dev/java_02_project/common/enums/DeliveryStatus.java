package com.sparta.dev.java_02_project.common.enums;

public enum DeliveryStatus {
    READY,                //배송 준비 중
    PICKED_UP,            //택배 기사 픽업 완료
    IN_TRANSIT,           //배송 중
    OUT_FOR_DELIVERY,     //배송 출발
    DELIVERED,            //배송 완료
    CANCELLED,            //취소됨
    RETURN_REQUESTED,     //반품 요청
    RETURNED             //반품 완료
}
