package com.sparta.java2.project.part1.commerce.domain.refund.mapper;

import com.sparta.java2.project.part1.commerce.domain.refund.dto.RefundResponse;
import com.sparta.java2.project.part1.commerce.domain.refund.entity.Refund;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefundMapper {
    RefundResponse toRefundResponse(Refund refund);
}
