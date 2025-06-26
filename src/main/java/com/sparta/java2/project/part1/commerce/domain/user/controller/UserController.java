package com.sparta.java2.project.part1.commerce.domain.user.controller;

import com.sparta.java2.project.part1.commerce.common.response.ApiResponse;
import com.sparta.java2.project.part1.commerce.domain.user.dto.*;
import com.sparta.java2.project.part1.commerce.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserSearchResponse>> getAll() {
        return ApiResponse.success(userService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(
            @PathVariable Long id) {
        return ApiResponse.success(userService.getById(id));
    }

    @PostMapping
    public ApiResponse<UserResponse> create(
            @Valid @RequestBody UserCreateRequest userCreateRequest) {
        return ApiResponse.success(userService.create(userCreateRequest));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.update(id, userUpdateRequest);
        return ApiResponse.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id) {
        userService.delete(id);
        return ApiResponse.success();
    }
}
