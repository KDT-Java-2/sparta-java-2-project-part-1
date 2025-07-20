package com.sparta.spartajava2projectpart1.domain.user.service;

import com.sparta.spartajava2projectpart1.common.exception.ServiceException;
import com.sparta.spartajava2projectpart1.common.exception.ServiceExceptionCode;
import com.sparta.spartajava2projectpart1.domain.user.dto.UserRequest;
import com.sparta.spartajava2projectpart1.domain.user.entity.User;
import com.sparta.spartajava2projectpart1.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(Long userId) {
        if (ObjectUtils.isEmpty(userId)) {
            throw new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA);
        }
        return User.builder().build();
    }

    public void save(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA);
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .passwordHash(request.getPassword()) // Security require
                .agree(request.getAgree())
                .thirdAgree(request.getThirdAgree())
                .marketing(request.getMarketing())
                .build();
        userRepository.save(user);
    }
}
