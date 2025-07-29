package com.sparta.commerce_project_01;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
public class RedisTest {

  private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

  @Autowired
  private Jedis jedis;

  @Test
  void testRedis() {
    jedis.set("key", "Hello Jedis!!");

    String value = jedis.get("key");

    log.info("value :  {}", value);

  }
}
