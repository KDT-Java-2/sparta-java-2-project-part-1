package com.sparta.commerce_project_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableAsync
@SpringBootApplication
@EnableRedisHttpSession
public class CommerceProject01Application {

  public static void main(String[] args) {
    SpringApplication.run(CommerceProject01Application.class, args);
  }
}
