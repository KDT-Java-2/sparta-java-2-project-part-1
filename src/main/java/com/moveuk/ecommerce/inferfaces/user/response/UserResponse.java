package com.moveuk.ecommerce.inferfaces.user.response;

import com.moveuk.ecommerce.application.user.response.UserResult;

public class UserResponse {

    public record UserInfoResponse(long id, String email, String username){
        public static UserResponse.UserInfoResponse of(UserResult.UserRegisterV1 userResult) {
            return new UserResponse.UserInfoResponse(userResult.id(), userResult.email(),  userResult.username());
        }
    }
}
