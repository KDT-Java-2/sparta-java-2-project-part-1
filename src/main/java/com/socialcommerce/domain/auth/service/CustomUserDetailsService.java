package com.socialcommerce.domain.auth.service;

import com.socialcommerce.domain.auth.dto.CustomUserDetails;
import com.socialcommerce.domain.user.entity.User;
import com.socialcommerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomUserDetailsService.class);

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    log.info("[loadUserByUsername] called with email: {}", email);

    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    return new CustomUserDetails(user);
  }
}