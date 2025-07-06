package com.sparta.commerce_project_01.global.aop;

import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  // Pointcut을 별도의 메서드로 정의하고 이름을 부여
  @Pointcut("execution(* com.sparta.commerce_project_01.domain..*(..))")
  private void allServiceMethods() {

  }

  // 1. execution: `controller` 패키지 내의 모든 메서드 실행 전에 적용
  @Before("execution(* com.sparta.commerce_project_01.domain..controller..*(..))")
  public void logBeforeApiExecution(JoinPoint joinPoint) {
    log.info("[API-execution] API 메서드 실행 전 로그");
  }

  // 2. within: `domain` 패키지 내의 모든 메서드 실행 전에 적용
  @Before("allServiceMethods()")
  public void logBeforeWithin(JoinPoint joinPoint) {
    log.info("[within] domain 패키지 내부 메서드 실행 전 로그");
  }

  // 3. @annotation: @Loggable 어노테이션이 붙은 메서드 실행 전에만 적용
  @Before("@annotation(com.sparta.commerce_project_01.common.annotation.Loggable)")
  public void logBeforeAnnotation(JoinPoint joinPoint) {
    log.info("[@annotation] @Loggable 어노테이션 적용된 메서드 실행 전 로그");
  }

  // 4. JoinPoint 활용: 메서드의 상세 정보 로깅
  @Before("allServiceMethods()")
  public void logMethodDetails(JoinPoint joinPoint) {
    log.info("실행된 메서드 이름: {}", joinPoint.getSignature().getName());
    Object[] args = joinPoint.getArgs();
    if (args.length > 0) {
      log.info("전달된 파라미터: {}", args);
    }
  }

  @AfterThrowing(pointcut = "allServiceMethods()", throwing = "serviceException")
  public void logServiceMethodExecution(ServiceException serviceException) {
    log.error("Service Method Execution Code: [{}], Error: [{}]", serviceException.getCode(),
        serviceException.getMessage());

  }
}