package com.sparta.spartajava2projectpart1.domain.user.repository;

import com.sparta.spartajava2projectpart1.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.email = :email")
    Optional<User> findUser(String name, String email);

    List<User> findAllByAgreeIsFalse();

    Optional<User> findUserByName(String name);

    @Query("SELECT u FROM User u JOIN FETCH u.purchases")
    List<User> findAllByWithPurchases();

}
