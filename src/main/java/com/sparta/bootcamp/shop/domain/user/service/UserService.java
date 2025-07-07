package com.sparta.bootcamp.shop.domain.user.service;

import com.sparta.bootcamp.shop.common.exception.ServiceException;
import com.sparta.bootcamp.shop.common.exception.ServiceExceptionCode;
import com.sparta.bootcamp.shop.domain.user.dto.UserCreateRequest;
import com.sparta.bootcamp.shop.domain.user.dto.UserResponse;
import com.sparta.bootcamp.shop.domain.user.dto.UserSearchResponse;
import com.sparta.bootcamp.shop.domain.user.dto.UserUpdateRequest;
import com.sparta.bootcamp.shop.domain.user.entity.User;
import com.sparta.bootcamp.shop.domain.user.mapper.UserMapper;
import com.sparta.bootcamp.shop.domain.user.repository.UserQueryRepository;
import com.sparta.bootcamp.shop.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;

    @Transactional
    public Page<UserSearchResponse> searchUser() {
        return null;
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));
        return null;
    }

    @Transactional
    public void create(UserCreateRequest request) {
        userRepository.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .passwordHash(request.getPassword())
                .build());
    }

    @Transactional
    public void update(Long userId, UserUpdateRequest request) {
        User user = getUser(userId);

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        userRepository.save(user);
    }

    @Transactional
    public void delete(Long userId) {
        userRepository.delete(getUser(userId));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));
    }


}