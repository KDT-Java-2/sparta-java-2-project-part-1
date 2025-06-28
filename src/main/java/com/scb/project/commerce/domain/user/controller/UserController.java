package com.scb.project.commerce.domain.user.controller;

import com.scb.project.commerce.common.response.ApiResponse;
import com.scb.project.commerce.domain.user.dto.UserSignUpRequest;
import com.scb.project.commerce.domain.user.dto.UserSignUpResponse;
import com.scb.project.commerce.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입 API
     * <p>새로운 사용자의 정보를 시스템에 등록합니다.</p>
     *
     * @param request 회원 가입 요청 정보 (이름, 이메일, 비밀번호)
     * @return 가입된 사용자 정보가 포함된 공통 응답 객체
     */
    @PostMapping
    public ApiResponse<UserSignUpResponse> signUp(@Valid @RequestBody UserSignUpRequest request) {
        return userService.signUp(request);
    }
}
