package com.sparta.javamarket.global.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  @Pointcut("execution(* com.sparta.javamarket.domain..*(..))")
  private void pointcutMethods() {
  }

  @Before("pointcutMethods()")
  public void logMethodDetails(JoinPoint joinPoint) {
    log.info("[JoinPoint 활용] 실행된 메서드 이름 : {}", joinPoint.getSignature().getName());
    Object[] args = joinPoint.getArgs();
    if (args.length > 0) {
      log.info("[JoinPoint 활용] 전달된 파라미터 : {}", args);
    }
  }

}
