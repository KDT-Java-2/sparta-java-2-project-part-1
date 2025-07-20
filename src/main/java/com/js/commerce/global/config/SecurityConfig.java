package com.js.commerce.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    // strength: work factor, 높을수록 해시 반복 횟수가 늘어나며 보안은 강해지지만 CPU 부하도 커집니다.
    return new BCryptPasswordEncoder(10);
  }
}
