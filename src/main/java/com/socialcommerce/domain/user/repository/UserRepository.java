package com.socialcommerce.domain.user.repository;

import com.socialcommerce.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByNameAndEmail(String name, String email);

  boolean existsByEmail(String email);
//  // find 와 get 의 차이점은 get 은 반드시있고, find 는 있을수도 없을수도 있다로 정의된다.
//  @Query("SELECT u FROM User u WHERE u.name = :name AND u.email = :email")
//  // JPQL 이라고 하는 쿼리, MySQL 이 아님, Java 내부에서 쓰이는 것인가봄
//  // :name ":" 은 파라메터의 이름이다. 같게 맞춰줘야한다.
//  // JPQL 은 좋지않다. 쿼리가 아니면 풀기 힘든경우에 사용하고, 위의 규격상으로 쓰는게 좋다.
//  // 왠만하면 사용하지 않는게 좋다.
//  Optional<User> findUser(String name, String email);
}
