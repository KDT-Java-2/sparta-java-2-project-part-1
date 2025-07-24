package com.sparta.bootcamp.java_2_example.domain.user.service;

import com.sparta.bootcamp.java_2_example.common.exception.ServiceException;
import com.sparta.bootcamp.java_2_example.common.exception.ServiceExceptionCode;
import com.sparta.bootcamp.java_2_example.domain.user.dto.UserCreateRequest;
import com.sparta.bootcamp.java_2_example.domain.user.dto.UserCreateResponse;
import com.sparta.bootcamp.java_2_example.domain.user.entity.User;
import com.sparta.bootcamp.java_2_example.domain.user.mapper.UserMapper;
import com.sparta.bootcamp.java_2_example.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserCreateResponse create(UserCreateRequest request) {
        ensurerEmailUnique(request.getEmail());

        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        User user = userRepository.save(userMapper.toEntity(request));

        return userMapper.toCreateResponse(user);
    }

    private void ensurerEmailUnique(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ServiceException(ServiceExceptionCode.ALREADY_EXISTS_EMAIL);
        }
    }
}
