package com.socialcommerce.domain.user.service;

import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.user.dto.UserCreateRequest;
import com.socialcommerce.domain.user.entity.User;
import com.socialcommerce.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public void create(UserCreateRequest request){
    
    // 이메일 중복확인 로직
    if(userRepository.existsByEmail(request.getEmail())){
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
    }
    // 폰번호, 패스워드 암호화하고나서 넣기

    
    userRepository.save(User.builder()
            .nickName(request.getNickName())
            .name(request.getName())
            .email(request.getEmail())
            .dateOfBirth(request.getDateOfBirth())
            .gender(request.getGender())
            .phoneNumberHash(request.getPhoneNumberHash())
            .passwordHash(request.getPasswordHash())
            .build());
  }
}
