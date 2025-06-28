package com.sparta.java_02.domain.user;

import com.sparta.java_02.domain.user.dto.UserSignupRequest;
import com.sparta.java_02.domain.user.dto.UserSignupResponse;
import com.sparta.java_02.global.exception.BusinessException;
import com.sparta.java_02.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignupResponse signup(UserSignupRequest request) {
        // 이메일 중복 검증
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 사용자 생성
        User user = new User(request.getEmail(), encodedPassword, request.getUsername());
        User savedUser = userRepository.save(user);

        return UserSignupResponse.from(savedUser);
    }
} 