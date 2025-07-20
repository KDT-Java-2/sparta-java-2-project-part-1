package com.sparta.spartajava2projectpart1.domain.user.controller;

import com.sparta.spartajava2projectpart1.common.response.ApiResponse;
import com.sparta.spartajava2projectpart1.domain.user.dto.UserRequest;
import com.sparta.spartajava2projectpart1.domain.user.dto.UserSearchResponse;
import com.sparta.spartajava2projectpart1.domain.user.dto.UserUpdateStatusRequest;
import com.sparta.spartajava2projectpart1.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ApiResponse<List<UserSearchResponse>> findAll(@RequestParam(required = false) String email, @PathVariable Long userId) {
        // GET /api/users?email="asdf@naver.com"
        return ApiResponse.success(List.of(UserSearchResponse.builder().build()));
    }

    @PostMapping
    public ApiResponse<Void> save(@RequestBody UserRequest request) {
        userService.save(request);
        return ApiResponse.success();
    }

    @PutMapping("/{userId}")
    public ApiResponse<Void> update(@PathVariable Long userId, @RequestBody UserRequest request) {
        return ApiResponse.success();
    }

    @PatchMapping("/{userId}")
    public ApiResponse<Void> updateStatus(@PathVariable Long userId, @RequestBody UserUpdateStatusRequest request) {
        return ApiResponse.success();
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<Void> delete(@PathVariable Long userId) {
        return ApiResponse.success();
    }
}
