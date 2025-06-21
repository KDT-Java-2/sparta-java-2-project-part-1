package me.chahyunho.projectweek1.domain.refunds.repository;

import java.util.List;
import me.chahyunho.projectweek1.domain.refunds.entity.Refunds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundsRepository extends JpaRepository<Refunds, Long> {

  @Query("SELECT r FROM Refunds r JOIN FETCH r.purchase p JOIN FETCH p.user")
  List<Refunds> findAllWithPurchaseAndUser();

}
