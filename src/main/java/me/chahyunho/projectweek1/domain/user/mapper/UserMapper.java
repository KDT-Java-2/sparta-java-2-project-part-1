package me.chahyunho.projectweek1.domain.user.mapper;


import me.chahyunho.projectweek1.domain.user.dto.UserRequest;
import me.chahyunho.projectweek1.domain.user.dto.UserResponse;
import me.chahyunho.projectweek1.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toEntity(UserRequest userRequest);

  UserResponse toUserResponse(User user);
}
