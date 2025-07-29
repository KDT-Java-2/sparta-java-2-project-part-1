package com.sparta.commerce_project_01.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @ExecutionLogging 커스텀 annotation 정의 방법(@interface로 정의)
@Retention(RetentionPolicy.RUNTIME)  // 실행 시에도 유지
@Target(ElementType.METHOD)          // 메서드에만 사용 가능
public @interface ExecutionLogging {

}