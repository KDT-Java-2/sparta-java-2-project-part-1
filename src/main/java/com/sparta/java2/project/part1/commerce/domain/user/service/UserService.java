package com.sparta.java2.project.part1.commerce.domain.user.service;

import com.sparta.java2.project.part1.commerce.common.exception.ServiceException;
import com.sparta.java2.project.part1.commerce.common.exception.ServiceExceptionCode;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserCreateRequest;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserResponse;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserSearchResponse;
import com.sparta.java2.project.part1.commerce.domain.user.dto.UserUpdateRequest;
import com.sparta.java2.project.part1.commerce.domain.user.mapper.UserMapper;
import com.sparta.java2.project.part1.commerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserResponse getById(Long id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER)));
    }

    public List<UserSearchResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toSearchResponse)
                .toList();
    }

    public UserResponse create(UserCreateRequest userCreateRequest) {
        return userMapper.toUserResponse(
                userRepository.save(userMapper.toUser(userCreateRequest))
        );
    }

    public void update(Long id, UserUpdateRequest userUpdateRequest) {
        userRepository.save(userMapper.toUser(userUpdateRequest));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
