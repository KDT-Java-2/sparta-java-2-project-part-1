package com.js.commerce.domain.user.repository;

import com.js.commerce.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository  // 구현체에 등록할 거라 선언체에 안 써도 되지만 명시하는 게 좋음
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByNameAndEmail(String name, String email);
  // 위와 정확히 동일한 결과 반환!! fetch join 필요한 거 아니면 최대한 @Query 사용 피할 것


  // 이렇게 하면 User 객체가 리턴됨. :name은 아래에서 인자로 받음
  @Query("SELECT u FROM User u WHERE u.name = :name AND u.email = :email")
  Optional<User> findUser(String name, String email);
  // find 자체가 무조건 값이 있다고 볼 수 없기 때문에 Optional로 서비스 로직에서 null 체크 하게 해 줌
  // @Query를 쓰면 JpaRepository의 네임 규칙을 회피하는 편

//  @Query("SELECT u FROM User u JOIN FETCH u.purchases")
//  List<User> findAllByWithPurchases();
}
