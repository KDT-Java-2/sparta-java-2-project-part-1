package com.moveuk.ecommerce.infra.user;

import com.moveuk.ecommerce.domain.user.User;
import com.moveuk.ecommerce.domain.user.UserProfile;
import com.moveuk.ecommerce.domain.user.UserProfileRepository;
import com.moveuk.ecommerce.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IUserRepository implements UserRepository, UserProfileRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserProfileRepository jpaUserProfileRepository;

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id);
    }

    public UserProfile save(UserProfile userProfile) {
        return jpaUserProfileRepository.save(userProfile);
    }
}
