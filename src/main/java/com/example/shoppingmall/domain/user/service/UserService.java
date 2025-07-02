package com.example.shoppingmall.domain.user.service;

import com.example.shoppingmall.common.exception.ServiceException;
import com.example.shoppingmall.common.exception.ServiceExceptionCode;
import com.example.shoppingmall.domain.user.dto.UserCreateRequest;
import com.example.shoppingmall.domain.user.dto.UserResponse;
import com.example.shoppingmall.domain.user.entity.User;
import com.example.shoppingmall.domain.user.mapper.UserMapper;
import com.example.shoppingmall.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        // 1. 이메일 중복 확인 (비즈니스 규칙)
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
        }

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. DTO를 Entity로 변환하여 DB에 저장
        User user = userMapper.toEntity(request, encodedPassword);
        User savedUser = userRepository.save(user);

        // 4. Entity를 Response DTO로 변환하여 Controller에 반환
        return userMapper.toResponse(savedUser);
    }

}
