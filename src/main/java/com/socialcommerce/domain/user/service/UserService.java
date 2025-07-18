package com.socialcommerce.domain.user.service;

import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.common.utils.HashUtil;
import com.socialcommerce.domain.user.dto.UserCreateRequest;
import com.socialcommerce.domain.user.dto.UserCreateResponse;
import com.socialcommerce.domain.user.entity.User;
import com.socialcommerce.domain.user.mapper.UserMapper;
import com.socialcommerce.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;
  private final HashUtil hashUtil;

  @Transactional
  public UserCreateResponse create(UserCreateRequest request){

    // 이메일 중복확인 로직
    if(userRepository.existsByEmail(request.getEmail())){
      throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
    }
    // 폰번호, 패스워드 암호화하고나서 넣기
    String passwordHash = passwordEncoder.encode(request.getPasswordHash());
    // SHA-256 with Salt 적용
    String phoneNumberHash = hashUtil.hashWithSalt(request.getPhoneNumberHash());

    User user = userRepository.save(User.builder()
            .nickName(request.getNickName())
            .name(request.getName())
            .email(request.getEmail())
            .dateOfBirth(request.getDateOfBirth())
            .gender(request.getGender())
            .phoneNumberHash(phoneNumberHash)
            .passwordHash(passwordHash)
            .build());

//    return UserCreateResponse.builder()
//        .id(user.getId())
//        .email(user.getEmail())
//        .username(user.getName())
//        .build();

    return userMapper.toCreateResponse(user);
  }



}
