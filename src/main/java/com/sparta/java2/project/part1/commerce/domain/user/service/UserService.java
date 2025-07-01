package com.sparta.java2.project.part1.commerce.domain.user.service;

import com.sparta.java2.project.part1.commerce.common.exception.ServiceException;
import com.sparta.java2.project.part1.commerce.common.exception.ServiceExceptionCode;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserResponse;
import com.sparta.java2.project.part1.commerce.domain.user.mapper.UserMapper;
import com.sparta.java2.project.part1.commerce.domain.user.entity.User;
import com.sparta.java2.project.part1.commerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        // Email 중복체크
        if (userRepository.findByEmail(userCreateRequest.getEmail()).isPresent())
            throw new ServiceException(ServiceExceptionCode.ALREADY_EXIST_EMAIL);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userCreateRequest.getPassword());

        return userMapper.toUserResponse(
                userRepository.save(
                        User.builder()
                                .name(userCreateRequest.getUsername())
                                .email(userCreateRequest.getEmail())
                                .passwordHash(encodedPassword)
                                .build()));

//        return userMapper.toUserResponse(
//                userRepository.save(
//                        userMapper.toUser(
//                                userCreateRequest
//                        )));
    }
}
