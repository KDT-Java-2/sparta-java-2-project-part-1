package com.sparta.bootcamp.java_2_example.domain.user.mapper;

import com.sparta.bootcamp.java_2_example.domain.user.dto.UserCreateRequest;
import com.sparta.bootcamp.java_2_example.domain.user.dto.UserCreateResponse;
import com.sparta.bootcamp.java_2_example.domain.user.entity.User;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2025-07-17T23:15:12+0900",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserCreateRequest Request) {
        if (Request == null) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.passwordHash(Request.getPassword());
        user.name(Request.getUsername());
        user.email(Request.getEmail());

        return user.build();
    }

    @Override
    public UserCreateResponse toCreateResponse(User user) {
        if (user == null) {
            return null;
        }

        String username = null;
        Long id = null;
        String email = null;

        username = user.getName();
        id = user.getId();
        email = user.getEmail();

        UserCreateResponse userCreateResponse = new UserCreateResponse(id, username, email);

        return userCreateResponse;
    }
}
