package com.scb.masterCourse.commerce.domain.user.service;

import com.scb.masterCourse.commerce.common.exception.ServiceException;
import com.scb.masterCourse.commerce.common.exception.ServiceExceptionCode;
import com.scb.masterCourse.commerce.domain.user.dto.UserCreateRequest;
import com.scb.masterCourse.commerce.domain.user.dto.UserCreateResponse;
import com.scb.masterCourse.commerce.domain.user.entity.User;
import com.scb.masterCourse.commerce.domain.user.mapper.UserMapper;
import com.scb.masterCourse.commerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    public UserCreateResponse signup(UserCreateRequest request) {

        Boolean isExistsNickname = userRepository.existsByNickname(request.getNickname());
        if (isExistsNickname) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_NICKNAME);
        }

        Boolean isExistsEmail = userRepository.existsByEmail(request.getEmail());
        if (isExistsEmail) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
        }

        User user = userMapper.toEntity(request);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
}
