package com.socialcommerce.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialcommerce.common.response.ApiResponse;
import com.socialcommerce.domain.auth.dto.CustomUserDetails;
import com.socialcommerce.domain.user.entity.User;
import com.socialcommerce.domain.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
  /*
   * OncePerRequestFilter를 상속받아 HTTP 요청마다 실행됩니다.
   * 이 필터는 Spring Security의 기본 인증 프로세스 전에 세션 정보를 확인하고,
   * 필요에 따라 인증을 처리합니다.
   *
   * */
  private final ObjectMapper objectMapper;
  private final UserRepository userRepository;

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthenticationFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // 인증이 필요한 경로인지 확인
    String requestURI = request.getRequestURI();
    if (isAuthenticationRequired(requestURI)) {
      try {
        // Spring Security 인증 정보 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
            "anonymousUser".equals(authentication.getPrincipal())) {

          // 세션에서 사용자 정보 확인
          HttpSession session = request.getSession(false);
          if (session != null) {
            Long userId = (Long) session.getAttribute("userId");
            String email = (String) session.getAttribute("email");

            if (!ObjectUtils.isEmpty(userId) && !ObjectUtils.isEmpty(email)) {
              // 세션 정보가 있으면 Spring Security 컨텍스트에 설정
              // 실제로는 CustomUserDetailsService를 통해 UserDetails를 가져와야 함
              // 세션에 사용자 정보가 존재 → SecurityContext에 Authentication 수동 주입 필요
              User user = userRepository.findById(userId)
                  .orElseThrow(() -> new RuntimeException("세션에 저장된 userId가 존재하지만 DB에 없음"));

              CustomUserDetails userDetails = new CustomUserDetails(user); // 여기서 User 주입
              UsernamePasswordAuthenticationToken authenticationToken =
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);

              log.info("Session 인증 정보로 SecurityContext 설정 완료: {}", email);
            } else {
              sendUnauthorizedResponse(response, "Authentication required");
              return;
            }
          } else {
            sendUnauthorizedResponse(response, "Authentication required");
            return;
          }
        } else {
          // Spring Security 인증 정보가 유효한 경우
          if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            log.info("Security authentication found for user: {}", userDetails.getUsername());
          }
        }
      } catch (Exception e) {
        log.error("Authentication filter error", e);
        sendUnauthorizedResponse(response, "Authentication failed");
        return;
      }
    }

    filterChain.doFilter(request, response);
  }

  private static final AntPathMatcher pathMatcher = new AntPathMatcher();

  private boolean isAuthenticationRequired(String requestURI) {
    String[] excludePaths = {
        "/api/auth/**",
        "/api/products/**",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/api/admin/**",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/api/users",
        "/api/users/availability",
        "/api/products",
        "/api/categories",
        "/actuator",
        "/public",
  };

    for (String pattern : excludePaths) {
      if (pathMatcher.match(pattern, requestURI)) {
        return false;
      }
    }

    return true;
  }


  // TODO: 추후 빌드시 추가 및 변경필요
//  private boolean isAuthenticationRequired(String requestURI) {
//    // 인증이 필요하지 않은 경로들
//    String[] excludePaths = {
//        "/api/admin/login",
//        "/api/admin/logout",
//        "/api/admin/products",
//        "/api/admin/categories",
//        "/api/admin/status",
//        "/api/users",
//        "/api/users/availability",
//        "/api/products",
//        "/api/categories",
//        "/swagger-ui/**",
//        "/v3/api-docs/**",
//        "/actuator",
//        "/public"
//    };
//
//    for (String excludePath : excludePaths) {
//      if (requestURI.startsWith(excludePath)) {
//        return false;
//      }
//    }
//
//    return true;
//  }

  private void sendUnauthorizedResponse(HttpServletResponse response, String message)
      throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json;charset=UTF-8");

    ApiResponse<Void> errorResponse = ApiResponse.<Void>builder()
        .result(false)
        .error(ApiResponse.Error.of("UNAUTHORIZED", message))
        .build();

    response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
  }
}
