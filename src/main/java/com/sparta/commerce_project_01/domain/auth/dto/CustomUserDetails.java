package com.sparta.commerce_project_01.domain.auth.dto;

import com.sparta.commerce_project_01.common.enums.UserRole;
import com.sparta.commerce_project_01.common.enums.UserStatus;
import com.sparta.commerce_project_01.domain.user.entity.User;
import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  @Getter
  private final Long userId;
  @Getter
  private final UserRole role;
  @Getter
  private final UserStatus status;

  private final String email;
  private final String passwordHash;

  public CustomUserDetails(User user) {
    this.userId = user.getId();
    this.email = user.getEmail();
    this.passwordHash = user.getPasswordHash();
    this.role = user.getRole();
    this.status = user.getStatus();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return passwordHash;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}