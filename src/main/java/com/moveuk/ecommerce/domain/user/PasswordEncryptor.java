package com.moveuk.ecommerce.domain.user;

public interface PasswordEncryptor {
    String encrypt(String raw);
    boolean matches(String raw, String hashed);
}
