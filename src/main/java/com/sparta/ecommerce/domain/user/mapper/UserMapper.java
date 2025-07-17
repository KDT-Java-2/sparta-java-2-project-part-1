package com.sparta.ecommerce.domain.user.mapper;

import com.sparta.ecommerce.domain.user.dto.UserCreateRequest;
import com.sparta.ecommerce.domain.user.dto.UserCreateResponse;
import com.sparta.ecommerce.domain.user.entity.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(source = "userNm", target = "username")
  UserCreateResponse toCreateResponse(User user);

  @Mapping(source = "username", target = "userNm")
  @Mapping(source = "password", target = "passwordHash", qualifiedByName = "encodePassword")
  User toEntity(UserCreateRequest request);

  @Named("encodePassword")
  static String encodePassword(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] encoded = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(encoded);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("암호화 실패", e);
    }
  }
}