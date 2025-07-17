package com.sparta.bootcamp.java_2_example.domain.user.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sparta.bootcamp.java_2_example.domain.user.dto.QUserCreateResponse is a Querydsl Projection type for UserCreateResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserCreateResponse extends ConstructorExpression<UserCreateResponse> {

    private static final long serialVersionUID = 1836888009L;

    public QUserCreateResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> username) {
        super(UserCreateResponse.class, new Class<?>[]{long.class, String.class, String.class}, id, email, username);
    }

}

