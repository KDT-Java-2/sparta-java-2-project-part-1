package com.sparta.coupang_commerce.infra.persistence.convertor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@Converter(autoApply = false)
public class CryptoConverter implements AttributeConverter<String, String> {

  private final TextEncryptor encryptor;

  public CryptoConverter(
      @Value("${app.crypto.secret-key}") String secretKey,
      @Value("${app.crypto.salt}") String salt
  ) {
    this.encryptor = Encryptors.text(secretKey, salt);
  }

  @Override
  public String convertToDatabaseColumn(String attribute) {
    return ObjectUtils.isEmpty(attribute) ? null : encryptor.encrypt(attribute);
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    return ObjectUtils.isEmpty(dbData) ? null : encryptor.decrypt(dbData);
  }
}
