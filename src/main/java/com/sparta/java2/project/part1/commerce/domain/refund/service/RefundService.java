package com.sparta.java2.project.part1.commerce.domain.refund.service;

import com.sparta.java2.project.part1.commerce.common.exception.ServiceException;
import com.sparta.java2.project.part1.commerce.common.exception.ServiceExceptionCode;
import com.sparta.java2.project.part1.commerce.domain.refund.dto.RefundResponse;
import com.sparta.java2.project.part1.commerce.domain.refund.mapper.RefundMapper;
import com.sparta.java2.project.part1.commerce.domain.refund.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundService {
    private final RefundMapper refundMapper;
    private final RefundRepository refundRepository;

    @Transactional
    public List<RefundResponse> getAll() {
        return refundRepository.findAll().stream()
                .map(refundMapper::toRefundResponse)
                .toList();
    }

    @Transactional
    public RefundResponse findById(Long id) {
        return refundMapper.toRefundResponse(refundRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_REFUND)));
    }
}
