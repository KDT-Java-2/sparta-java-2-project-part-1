package com.scb.project.commerce.domain.user.mapper;

import com.scb.project.commerce.domain.user.dto.UserSignUpRequest;
import com.scb.project.commerce.domain.user.dto.UserSignUpResponse;
import com.scb.project.commerce.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * 회원가입 요청 DTO를 User 엔티티로 변환합니다.
     *
     * @param request 회원가입 요청 정보
     * @return User 엔티티 객체
     */
    @Mapping(target = "passwordHash", source = "password")
    User toEntity(UserSignUpRequest request);

    /**
     * User 엔티티를 회원가입 응답 DTO로 변환합니다.
     *
     * @param user 저장된 사용자 엔티티
     * @return 회원가입 응답 DTO
     */
    @Mapping(target = "username", source = "name")
    UserSignUpResponse toResponse(User user);
}
