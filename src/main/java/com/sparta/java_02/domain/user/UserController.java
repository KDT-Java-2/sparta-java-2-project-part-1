package com.sparta.java_02.domain.user;

import com.sparta.java_02.domain.user.dto.UserSignupRequest;
import com.sparta.java_02.domain.user.dto.UserSignupResponse;
import com.sparta.java_02.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입
     * POST /api/users
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UserSignupResponse>> signup(
            @Valid @RequestBody UserSignupRequest request) {
        
        UserSignupResponse response = userService.signup(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
} 