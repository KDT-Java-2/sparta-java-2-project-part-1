package com.moveuk.ecommerce.infra.user;

import com.moveuk.ecommerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {

}
