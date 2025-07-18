package com.socialcommerce.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {

  @Value("${spring.data.redis.host}")  // yml 에 있는 데이터를 가져오기가 가능해진다. yml 안에 단계적 경로느낌으로 가져오면됌.
  private String redisHost;
  @Value("${spring.data.redis.port}")  // yml 에 있는 데이터를 가져오기가 가능해진다.
  private int redisPort;
  @Value("${spring.data.redis.password:}")
  // yml 에 있는 데이터를 가져오기가 가능해진다.
  // password 뒤에 : 가 붙은 이유는 필드에 데이터가 없을수도있다 라는 뜻이다.
  private String redisPassword;

  @Bean
  public Jedis jedis() {
    Jedis jedis = new Jedis(redisHost, redisPort);

    // 실무에서는 password 당연히 쓸거고, 없는경우는 local 일 경우인듯
    if (!ObjectUtils.isEmpty(redisPassword)) {
      jedis.auth(redisPassword);
    }

    return jedis;
  }

}
