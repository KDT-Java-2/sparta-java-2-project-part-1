package com.sparta.project1.domain.user.service;

import com.sparta.project1.common.exception.ServiceException;
import com.sparta.project1.common.exception.ServiceExceptionCode;
import com.sparta.project1.domain.user.dto.UserResponse;
import com.sparta.project1.domain.user.entity.User;
import com.sparta.project1.domain.user.mapper.UserMapper;
import com.sparta.project1.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserResponse> searchUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toSearch)
                .toList();
    }

    // 변경수행 전 대상조회 공통메소드
    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));
    }
}
