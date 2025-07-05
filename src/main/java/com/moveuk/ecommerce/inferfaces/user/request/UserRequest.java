package com.moveuk.ecommerce.inferfaces.user.request;

import com.moveuk.ecommerce.application.user.request.UserInfo;

public class UserRequest {
    public record UserRegisterV1(String email, String username, String password) {
        public static UserInfo.UserRegisterV1 from(UserRegisterV1 userRequest) {
            return new UserInfo.UserRegisterV1(userRequest.email(), userRequest.username(), userRequest.password());
        }
    }
}
