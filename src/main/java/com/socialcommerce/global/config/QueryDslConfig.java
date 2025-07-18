package com.socialcommerce.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* QueryDSL Setting */
@Configuration
public class QueryDslConfig {

  // 스프링이 사용하고있는 EntityMananger 를 자동생성(객체화)해준다.
  @PersistenceContext
  private EntityManager entityManager;

  // 스프링에 쓸수있게끔 설정을 넣어준다 : Bean
  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    // entityManager 는 빈깡통인데 @PersistenceContext 에 의해서 생성되었기 때문에 그대로 전달가능
    // 전달하면 그대로 사용할 수 있다.
    return new JPAQueryFactory(entityManager);
  }
}
