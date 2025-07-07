package com.sparta.commerce.common.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.function.Function;

public class QueryUtils {

  public static <T> BooleanExpression ifNotNull(T value, Function<T, BooleanExpression> condition){
    return value == null ? null : condition.apply(value);
  }
}
