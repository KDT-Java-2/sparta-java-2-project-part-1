package com.sparta.commerce_project_01.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogExecutionTimeAspect {

  // Pointcut: Service 계층의 모든 메서드를 대상으로 지정
  @Pointcut("execution(* com.sparta.commerce_project_01.domain..service..*(..))")
  private void allServiceMethods() {
  }

  // @Around는 ProceedingJoinPoint라는 특별한 객체를 사용
  @Around("allServiceMethods()")
  public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    // 1. 메서드 실행 전: 시작 시간 기록
    long startTime = System.currentTimeMillis();
    log.info("Service Method {} Execution Start Time: [{}]", joinPoint.getSignature(), startTime);

    // 2. 실제 타겟 메서드 실행
    Object result = joinPoint.proceed();

    // 3. 메서드 실행 후: 종료 시간 기록 및 실행 시간 계산/로깅
    long endTime = System.currentTimeMillis();
    log.info("Service Method {} End Time: [{}], Execution Time: [{}]", joinPoint.getSignature(),
        endTime, endTime - startTime);

    // 4. 원래 메서드의 실행 결과를 반환
    return result;
  }
}
