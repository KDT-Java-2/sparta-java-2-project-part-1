package com.sparta.project1.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {
  @PersistenceContext
  private EntityManager em; //JPAQueryFactory 소속이기에 필요

  @Bean // DI 활용을 위해 직접 스프링에 빈주입요청, 여기저기 매번 생성 번거로우니 공통로직화
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(em);
  }
}
