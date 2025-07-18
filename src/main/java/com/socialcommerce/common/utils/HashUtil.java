package com.socialcommerce.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class HashUtil {

  private final String salt;

  public HashUtil(String salt) {
    this.salt = salt;
  }

  public String hashWithSalt(String value) {
    try {
      String salted = value + salt;
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(salted.getBytes(StandardCharsets.UTF_8));
      // Base64 인코딩 (DB 저장/전송에 적합)
      return Base64.getEncoder().encodeToString(hash);
    } catch (Exception e) {
      throw new RuntimeException("해시 생성 실패", e);
    }
  }
}
