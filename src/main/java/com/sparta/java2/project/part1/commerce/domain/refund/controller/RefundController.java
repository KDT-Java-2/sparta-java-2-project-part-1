package com.sparta.java2.project.part1.commerce.domain.refund.controller;

import com.sparta.java2.project.part1.commerce.common.response.ApiResponse;
import com.sparta.java2.project.part1.commerce.domain.refund.dto.RefundCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.refund.dto.RefundResponse;
import com.sparta.java2.project.part1.commerce.domain.refund.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refunds")
public class RefundController {
    private final RefundService refundService;

    @GetMapping
    public ApiResponse<List<RefundResponse>> getAll() {
        return ApiResponse.success(refundService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<RefundResponse> findById(@PathVariable Long id) {
        return ApiResponse.success(refundService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RefundResponse> create(@RequestBody RefundCreateRequest refundCreateRequest) {
        return ApiResponse.success();
    }
}
