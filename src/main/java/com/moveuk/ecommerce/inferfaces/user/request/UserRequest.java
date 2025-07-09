package com.moveuk.ecommerce.inferfaces.user.request;

import com.moveuk.ecommerce.application.user.request.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UserRequest {
    public record UserRegisterV1(
            @Email(message = "유효한 이메일 주소를 입력해주세요")
            @NotBlank(message = "이메일은 필수입니다")
            String email,
            @NotBlank(message = "사용자 이름은 필수입니다")
            @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하여야 합니다")
            String username,
            @NotBlank(message = "비밀번호는 필수입니다")
            @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다")
            String password
    ) {
        public static UserInfo.UserRegisterV1 from(UserRegisterV1 userRequest) {
            return new UserInfo.UserRegisterV1(userRequest.email(), userRequest.username(), userRequest.password());
        }
    }
}
