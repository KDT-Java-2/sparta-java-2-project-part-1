package com.moveuk.ecommerce.infra.user;

import com.moveuk.ecommerce.domain.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserProfileRepository extends JpaRepository<UserProfile, Long> {
}
