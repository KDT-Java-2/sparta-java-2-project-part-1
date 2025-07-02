package com.example.shoppingmall.domain.user.mapper;

import com.example.shoppingmall.domain.user.dto.UserCreateRequest;
import com.example.shoppingmall.domain.user.dto.UserResponse;
import com.example.shoppingmall.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // User Entity -> UserResponse DTO 변환
    UserResponse toResponse(User user);

    // UserRequest DTO + 암호화된 비밀번호 -> User Entity 변환
    @Mappings({
        @Mapping(target = "passwordHash", source = "encodedPassword"),
    })
    User toEntity(UserCreateRequest request, String encodedPassword);
}
