package me.chahyunho.projectweek1.domain.purchase.repository;


import java.util.List;
import me.chahyunho.projectweek1.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  //@Query("SELECT DISTINCT p FROM Purchase p JOIN FETCH p.user")
  //  @EntityGraph(attributePaths = {"user"})
  //  @Query("SELECT p FROM Purchase p")
  @Query("SELECT p FROM Purchase p JOIN FETCH p.user")
  List<Purchase> findAllWithUsers();
}
