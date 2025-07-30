package com.socialcommerce.domain.auth.service;

import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.domain.auth.dto.LoginRequest;
import com.socialcommerce.domain.auth.dto.LoginResponse;
import com.socialcommerce.domain.user.entity.User;
import com.socialcommerce.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  /*
  * 입력된 이메일로 사용자를 조회하고,
  * PasswordEncoder를 사용하여 입력된 비밀번호와 데이터베이스에
  * 저장된 해시된 비밀번호를 비교합니다.
  * */
  @Transactional
  public LoginResponse login(LoginRequest loginRequest) {
    User user = userRepository.findByEmail(loginRequest.getEmail())
        .orElseThrow(() ->
            new ServiceException(ServiceExceptionCode.NOT_FOUND_USER)
        );
    // passwordEncoder 를 사용해서 비밀번호 비교
    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
      throw new ServiceException(ServiceExceptionCode.NOT_FOUND_USER);
    }

    // AuthenticationManager 를 사용하여 사용자를 인증
    // 여기서 내부적으로 CustomUserDetailsService.loadUserByUsername(email) 를 호출
    // DB 에서 User 를 조회 -> CustomUserDetails DTO 를 통해 사용자권한(getAuthorities)을 반환
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
    );

    // 현재 스레드의 SecurityContext에 인증 정보를 저장
    // 이 정보는 Spring Security가 세션을 통해 관리하게 됩니다.
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // 패스워드 검증
    return LoginResponse.builder()
        .userId(user.getId())
        .email(loginRequest.getEmail())
        .build();
  }

  public void logout() {
    SecurityContextHolder.clearContext();
  }

  public LoginResponse getLoginResponse(Long userId, String email) {
    return LoginResponse.builder()
        .userId(userId)
        .email(email)
        .build();
  }
}
