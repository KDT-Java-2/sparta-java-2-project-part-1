package com.sparta.java_02.domain.order.controller;

import com.sparta.java_02.common.dto.ApiResponse; // ApiResponse import
import com.sparta.java_02.domain.order.dto.OrderCreateRequestDto;
import com.sparta.java_02.domain.order.dto.OrderResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // [POST] 주문 생성 API
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDto>> createOrder( // 반환 타입 변경
                                                                      @Valid @RequestBody OrderCreateRequestDto requestDto) {

        // ** (Service, Repository 계층 생략) **
        // Service 로직을 대체하는 가상의 응답 DTO 생성
        OrderResponseDto responseDto = new OrderResponseDto(
                1L,
                requestDto.getProductId(),
                requestDto.getQuantity(),
                "PENDING",
                LocalDateTime.now()
        );

        // ApiResponse를 사용하여 성공 응답 생성
        ApiResponse<OrderResponseDto> apiResponse = ApiResponse.success(HttpStatus.CREATED, responseDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // [GET] 단일 주문 조회 API
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrder(@PathVariable Long orderId) { // 반환 타입 변경

        // ** (Service, Repository 계층 생략) **
        // Service 로직을 대체하는 가상의 응답 DTO 생성
        OrderResponseDto responseDto = new OrderResponseDto(
                orderId,
                100L,
                2,
                "COMPLETED",
                LocalDateTime.now().minusHours(1)
        );

        // ApiResponse를 사용하여 성공 응답 생성
        ApiResponse<OrderResponseDto> apiResponse = ApiResponse.success(responseDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}