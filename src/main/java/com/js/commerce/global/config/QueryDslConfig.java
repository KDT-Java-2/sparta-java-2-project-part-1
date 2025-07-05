package com.js.commerce.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {

  @PersistenceContext // entityManager를 스프링이 사용하고 있는 QueryDSL의 jpaQueryFactory에 넣어주는 어노테이션
  private EntityManager entityManager;

  @Bean // new JPAQueryFactory 이렇게 생성된 인스턴스를 스프링 컨테이너에 스프링 빈으로 등록하여 싱글톤으로 관리되게 함
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager); // JPA에 갖고 있는 커넥션 풀들을 QueryDSL에서도 쓸 수 있게 됨
    // QueryDSL에서 쿼리가 만들어진 것을 엔티티 매니저를 통해 JPA에 전달할 수 있게 됨
  }
}
