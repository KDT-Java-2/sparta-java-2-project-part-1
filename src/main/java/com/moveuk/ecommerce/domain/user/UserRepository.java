package com.moveuk.ecommerce.domain.user;

import java.util.Optional;

public interface UserRepository {
    User save (User user);

    Optional<User> findById(Long id);
}
