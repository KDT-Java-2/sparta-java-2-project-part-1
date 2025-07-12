package com.sparta.commerce.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 이 과정을 거쳐랴 jpa와 쿼리 dsl의 커넥션 풀을 공유함
@Configuration
public class QueryDslConfig {

  @PersistenceContext // 스프링이 사용하고 있는 엔티티 매니저를 받아서 넣어줌
  private EntityManager entityManager;

  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }
}

