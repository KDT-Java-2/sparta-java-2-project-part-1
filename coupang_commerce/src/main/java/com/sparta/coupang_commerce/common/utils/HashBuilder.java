package com.sparta.coupang_commerce.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public final class HashBuilder {

  private HashBuilder() {
    // 인스턴스화 방지
  }

  /**
   * 입력 값을 SHA-256 해싱하여 16진 문자열로 반환합니다.
   *
   * @param input 해시할 원본 문자열
   * @return 16진 해시 문자열 (소문자)
   */
  public static String sha256(String input) {
    return sha256(input, null);
  }

  /**
   * 입력 값 + 솔트를 SHA-256 해싱하여 16진 문자열로 반환합니다.
   *
   * @param input 해시할 원본 문자열
   * @param salt  추가할 솔트 문자열 (null 이면 솔트 미적용)
   * @return 16진 해시 문자열 (소문자)
   */
  public static String sha256(String input, String salt) {
    if (input == null) {
      throw new IllegalArgumentException("Input for hashing must not be null");
    }
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      String toHash = (salt != null) ? input + salt : input;
      byte[] hashed = md.digest(toHash.getBytes(StandardCharsets.UTF_8));

      // 바이트 배열을 16진 문자열로 변환
      StringBuilder hex = new StringBuilder(2 * hashed.length);
      for (byte b : hashed) {
        hex.append(String.format("%02x", b));
      }
      return hex.toString();
    } catch (NoSuchAlgorithmException e) {
      // SHA-256 알고리즘이 없으면 복구 불가능한 치명적 에러
      throw new IllegalStateException("SHA-256 algorithm not available", e);
    }
  }
}
