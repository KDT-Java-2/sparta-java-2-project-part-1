package com.sparta.java_02;

import com.sparta.java_02.domain.user.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Java02Application {

  public static void main(String[] args) {
    SpringApplication.run(Java02Application.class, args);

    User user = User.builder()
        .username("이름")
        .email("이메일")
        .build();
  }

}
