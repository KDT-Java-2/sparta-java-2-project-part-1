package com.scb.project.commerce.domain.user.service;

import com.scb.project.commerce.common.exception.ServiceException;
import com.scb.project.commerce.common.exception.ServiceExceptionCode;
import com.scb.project.commerce.common.response.ApiResponse;
import com.scb.project.commerce.domain.user.dto.UserSignUpRequest;
import com.scb.project.commerce.domain.user.dto.UserSignUpResponse;
import com.scb.project.commerce.domain.user.entity.User;
import com.scb.project.commerce.domain.user.mapper.UserMapper;
import com.scb.project.commerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * 회원 가입 기능
     * <p>사용자의 이메일 중복을 확인하고, 새 계정을 생성하여 저장합니다.</p>
     *
     * @param request 회원 가입 요청 정보 (이름, 이메일, 비밀번호 포함)
     * @return 생성된 사용자 정보를 담은 ApiResponse 객체
     * @throws ServiceException 이메일 중복 시 DUPLICATE_EMAIL 예외 발생
     */
    public ApiResponse<UserSignUpResponse> signUp(UserSignUpRequest request) {
        // 이메일 중복 체크
        boolean existsByEmail = userRepository.existsByEmail(request.getEmail());

        if (existsByEmail) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
        }

        // 회원가입
        User user = userMapper.toEntity(request);

        User savedUser = userRepository.save(user);

        return ApiResponse.success(userMapper.toResponse(savedUser));
    }
}
