package com.moveuk.ecommerce.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    public final UserProfileRepository userProfileRepository;

    public User joinUser(String email, String password, PasswordEncryptor passwordEncryptor) {
        User user = User.create(email, password, passwordEncryptor);
        userRepository.save(user);
        return  user;
    }

    public UserProfile joinUserProfile(Long id, String username) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. id=" + id));

        UserProfile userProfile = UserProfile.builder().user(user).username(username).build();
        userProfileRepository.save(userProfile);
        return userProfile;
    }
}
