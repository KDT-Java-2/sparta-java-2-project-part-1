package com.sparta.javamarket.domain.user.repository;

import com.sparta.javamarket.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//
//  @Query("SELECT u FROM User u JOIN FETCH u.purchases")
//  List<User> findAllByWithPurchases();

  List<User> findFirstByName(String name);

  List<User> findByName(String name);

  User findFirstByEmail(String email);

}
