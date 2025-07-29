package com.sparta.commerce_project_01.global.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {

  @Value("${spring.data.redis.host}")
  private String redisHost;

  @Value("${spring.data.redis.port}")
  private int redisPort;

  // 필드에 데이타가 없다는 표시 ':"
  @Value("${spring.data.redis.password:}")
  private String redisPassword;

  @Bean // 스프링 컨테이너에 Jedis 인스턴스를 빈으로 등록
  public Jedis jedis() {
    Jedis jedis = new Jedis(redisHost, redisPort);

    if (!ObjectUtils.isEmpty(redisPassword)) {
      jedis.auth(redisPassword);
    }
    return jedis;
  }
}





