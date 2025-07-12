package com.sparta.javamarket.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExecutionTimeAspect {


  @Pointcut("execution(* com.sparta.javamarket.domain..*(..))")
  private void pointcutMethods() {
  }

  @Around("pointcutMethods()")
  public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

    long startTime = System.currentTimeMillis();

    Object result = joinPoint.proceed();

    long executionTime = System.currentTimeMillis() - startTime;

    log.info("'{}' 메서드 실행 시간 : {}ms {}s", joinPoint.getSignature().toShortString(), executionTime, executionTime/1000.0);

    return result;

  }

}
