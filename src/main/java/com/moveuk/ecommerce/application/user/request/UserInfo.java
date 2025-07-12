package com.moveuk.ecommerce.application.user.request;

public class UserInfo {
    public record UserRegisterV1(String email, String username, String password) {
    }
}
