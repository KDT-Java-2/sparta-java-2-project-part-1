package com.sparta.commerce_project_01.global.aop;

import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionLoggingAspect {
    
  private static final String EXCEPTION_LOG_MESSAGE =
      "Service Layer Exception: Code = [{}], Message = [{}]";

  // Pointcut: Service 계층의 모든 메서드를 대상으로 지정
  @Pointcut("execution(* com.sparta.commerce_project_01.domain..*(..))")
  private void allServiceMethods() {
  }

  // Advice: serviceMethods Pointcut에서 ServiceException 타입의 예외 발생 시 동작
  @AfterThrowing(pointcut = "allServiceMethods()", throwing = "exception")
  public void logServiceExceptions(ServiceException exception) {

    log.error(EXCEPTION_LOG_MESSAGE,
        exception.getCode(),
        exception.getMessage());
  }
}
