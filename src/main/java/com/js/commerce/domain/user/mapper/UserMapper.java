package com.js.commerce.domain.user.mapper;

import com.js.commerce.domain.user.dto.UserCreateRequest;
import com.js.commerce.domain.user.dto.UserResponse;
import com.js.commerce.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

  @Autowired
  protected PasswordEncoder passwordEncoder;

  @Mapping(source = "username", target = "name")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "password", target = "passwordHash",
      qualifiedByName = "encodePassword")
  // qualifiedByName를 쓰면, MapStruct가 매핑할 때 특정 이름으로 등록된 메서드를 호출하도록 지시함
  public abstract User toEntity(UserCreateRequest request);

//  /** 요청 DTO → 엔티티 (직접 구현 방식. 위와 같음) **/
//  public User toEntity(UserCreateRequest req) {
//    return User.builder()
//        .name(req.getUsername())
//        .email(req.getEmail())
//        .passwordHash(passwordEncoder.encode(req.getPassword()))
//        .build();
//  }

  @Mapping(source = "name", target = "username")
  public abstract UserResponse toResponse(User user);

  @Named("encodePassword")
  protected String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
