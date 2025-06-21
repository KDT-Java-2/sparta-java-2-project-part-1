package com.sparta.sparta_java_2_project_part_1.domain.user.repository;

import com.sparta.sparta_java_2_project_part_1.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//  Optional<User> findFirstByNameAndEmail(String email);
//
//  @Query("SELECT u FROM User u WHERE u.name = :name")
//  Optional<User> findUser(String name);
}
