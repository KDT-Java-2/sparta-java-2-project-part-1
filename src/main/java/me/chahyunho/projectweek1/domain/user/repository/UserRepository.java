package me.chahyunho.projectweek1.domain.user.repository;

import java.util.List;
import java.util.Optional;
import me.chahyunho.projectweek1.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findFirstByName(String name);

  @Query("SELECT u FROM User u JOIN FETCH u.purchases")
  List<User> findAllWithPurchases();
}
