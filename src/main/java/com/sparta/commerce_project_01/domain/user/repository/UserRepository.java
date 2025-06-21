package com.sparta.commerce_project_01.domain.user.repository;

import com.sparta.commerce_project_01.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNameAndEmail(String name, String email);

    @Query("select u from User u where u.name = :name and u.email = :email")
    Optional<User> findUser(String name, String email);
    // find : 있을수도 없을 수도 있다
    // get :  무조건 있을 경우
}
