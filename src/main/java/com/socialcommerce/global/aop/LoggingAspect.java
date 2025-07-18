package com.socialcommerce.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  //// Spring AOP 란 메서드 실행 전/후에 코드를 끼워넣어서
  //// 프록시(가짜 클래스) 를 만들고 대신 동작시키는 것
  //// 이부분때문에 Security 랑 충돌에러남
  //// AuthenticationFilter 에 doFilterInternal 는 OncePerRequestFilter 를 상속받아서
  //// 구현해야하는 함수인데 이 함수는 인증을 위해서 Filter 를 거는것인데 final 로 존재함.
  //// 그래서 final 은 오버라이딩이 불가능하므로 새롭게 상속받아서 사용할 수 없다.

  /*
  * class Target {
    public String hello(String name) {
          return "Hello " + name;
      }
    }
    * Target 의 프록시는 대략
    *
    * class Target$$Proxy extends Target {
    @Override
    public String hello(String name) {
          System.out.println("Before Method Call");
          return super.hello(name);
      }
    }
    * 이런 느낌이라고 보면됌.
  * */

//  @Before("execution(* com.sparta..*(..))")
//  public void logAnyMethod() {
//    log.info("===== Before All Method !=====");
//  }

  // 1. execution: `api` 패키지 내의 모든 메서드 실행 전에 적용
  @Before("execution(* com.socialcommerce.domain..controller..*(..))")
  public void logBeforeApiExecution() {
    log.info("[API-execution] API 메서드 실행 전 로그");
  }

  // 2. within: `domain` 패키지 내의 모든 메서드 실행 전에 적용
  @Before("within(com.socialcommerce.domain..*)")
  public void logBeforeWithin() {
    log.info("[within] domain 패키지 내부 메서드 실행 전 로그");
  }

  // 3. @annotation: @Loggable 어노테이션이 붙은 메서드 실행 전에만 적용
  @Before("@annotation(com.socialcommerce.common.annotation.Loggable)")
  public void logBeforeAnnotation() {
    log.info("[@annotation] @Loggable 어노테이션 적용된 메서드 실행 전 로그");
  }

  // 4. JoinPoint 활용: 메서드의 상세 정보 로깅
  @Before("execution(* com.socialcommerce.domain..*(..))")
  public void logMethodDetails(JoinPoint joinPoint) {
    log.info("실행된 메서드 이름: {}", joinPoint.getSignature().getName());
    Object[] args = joinPoint.getArgs();
    if (args.length > 0) {
      log.info("전달된 파라미터: {}", args);
    }
  }
}
