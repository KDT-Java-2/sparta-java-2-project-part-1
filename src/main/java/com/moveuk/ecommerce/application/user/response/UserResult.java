package com.moveuk.ecommerce.application.user.response;

public class UserResult {
    public record UserRegisterV1(Long id, String email, String username) {
        public static UserRegisterV1 from(Long id, String email, String username) {
            return new UserRegisterV1(
                    id, email, username
            );
        }
    }
}