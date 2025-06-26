package com.sparta.java2.project.part1.commerce.domain.refund.dto;

import com.sparta.java2.project.part1.commerce.common.enums.RefundStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefundResponse {
    Long id;
    Long purchase_id;
    String reason;
    RefundStatus status;
}
