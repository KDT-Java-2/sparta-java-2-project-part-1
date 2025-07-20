package com.moveuk.ecommerce.application.user;

import com.moveuk.ecommerce.application.user.request.UserInfo;
import com.moveuk.ecommerce.application.user.response.UserResult;
import com.moveuk.ecommerce.domain.user.PasswordEncryptor;
import com.moveuk.ecommerce.domain.user.User;
import com.moveuk.ecommerce.domain.user.UserProfile;
import com.moveuk.ecommerce.domain.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final PasswordEncryptor passwordEncryptor;

    @Transactional
    public UserResult.UserRegisterV1 joinUser(UserInfo.UserRegisterV1 from) {
        User user = userService.joinUser(from.email(), from.password(), passwordEncryptor);
        UserProfile userProfile = userService.joinUserProfile(user.getId(), from.username());
        return UserResult.UserRegisterV1.from(user.getId(), user.getEmail(), userProfile.getUsername());
    }
}
