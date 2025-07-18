package com.socialcommerce.global.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.socialcommerce.common.utils.HashUtil;
import com.socialcommerce.global.filter.AuthenticationFilter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private static final String[] SECURITY_EXCLUDE_PATHS = {
      "/public/**", "/api/swagger-ui/**", "/swagger-ui/**", "/swagger-ui.html",
      "/api/v3/api-docs/**", "/v3/api-docs/**", "/favicon.ico", "/actuator/**",
      "/swagger-resources/**", "/external/**", "/api/auth/**", "/api/users/availability",
      "/api/users",
  };

  private final AuthenticationFilter authenticationFilter;

  public SecurityConfig(AuthenticationFilter authenticationFilter) {
    this.authenticationFilter = authenticationFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(SECURITY_EXCLUDE_PATHS).permitAll()  // 정의된 경로는 인증없이 접근허용설정
            .requestMatchers("/api/user").hasRole("USER") // ROLE_ 접두사 자동 추가
            .requestMatchers("/api/admin").hasRole("ADMIN") // ROLE_ 접두사 자동 추가
            .anyRequest().authenticated() // 그 외 모든 요청은 인증된 사용자만 접근
        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션이 필요할 경우에만 생성
            .maximumSessions(1) // 한 사용자가 동시에 가질 수 있는 세션 수를 1로 제한
            .maxSessionsPreventsLogin(false) // 이미 로그인된 세션이 있을 경우, 새 로그인을 차단하지 않고 기존 세션을 만료시킵니다.
        )
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    // UsernamePasswordAuthenticationFilter 이전에 AuthenticationFilter를 추가하여 인증 프로세스를 커스터마이징합니다.

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public HashUtil hashUtil(@Value("${security.hash-salt}") String salt) {
    return new HashUtil(salt);
  }
}
