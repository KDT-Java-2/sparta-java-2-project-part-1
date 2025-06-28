package com.sparta.spartajava2projectpart1.domain.user.controller;

import com.sparta.spartajava2projectpart1.domain.user.dto.UserRequest;
import com.sparta.spartajava2projectpart1.domain.user.dto.UserSearchResponse;
import com.sparta.spartajava2projectpart1.domain.user.dto.UserUpdateStatusRequest;
import com.sparta.spartajava2projectpart1.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserSearchResponse>> findAll(@RequestParam(required = false) String email, @PathVariable Long userId) {
        // GET /api/users?email="asdf@naver.com"
        return ResponseEntity.ok(List.of(UserSearchResponse.builder().build()));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UserRequest request) {
        userService.save(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable Long userId, @RequestBody UserRequest request) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long userId, @RequestBody UserUpdateStatusRequest request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        return ResponseEntity.ok().build();
    }
}
