package com.sparta.commerce_project_01.domain.user.repository;

import com.sparta.commerce_project_01.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // find : 있을수도 없을 수도 있다
  // get :  무조건 있을 경우

  // 이메일로 유저 조회 (결과가 없을 수 있으므로 Optional 사용)
  Optional<User> findByEmail(String email);

  Optional<User> findFirstByName(String name);

  // 특정 날짜 이후에 가입한 유저들을 이름 순으로 정렬하여 조회
  List<User> findByCreatedAtAfterOrderByNameAsc(LocalDateTime dateTime);

  // 'role'이 'admin'인 유저의 수를 카운트
  long countByRole(String role);

  Optional<User> findByNameAndEmail(String name, String email);

  @Query("select u from User u where u.name = :name and u.email = :email")
  Optional<User> findUser(String name, String email);

  Optional<User> findUserById(Long id);

}
