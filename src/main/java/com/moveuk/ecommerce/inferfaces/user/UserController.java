package com.moveuk.ecommerce.inferfaces.user;

import com.moveuk.ecommerce.application.user.UserFacade;
import com.moveuk.ecommerce.application.user.response.UserResult;
import com.moveuk.ecommerce.inferfaces.user.request.UserRequest;
import com.moveuk.ecommerce.inferfaces.user.response.UserResponse;
import com.moveuk.ecommerce.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/api/users")
    public ApiResponse<UserResponse.UserInfoResponse> joinUser(@RequestBody UserRequest.UserRegisterV1 userRequest) {
        UserResult.UserRegisterV1 response = userFacade.joinUser(UserRequest.UserRegisterV1.from(userRequest));
        return ApiResponse.success(UserResponse.UserInfoResponse.of(response));
    }


}
