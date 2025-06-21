package me.chahyunho.projectweek1.domain.refunds.repository;

import me.chahyunho.projectweek1.domain.refunds.entity.Refunds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundsRepository extends JpaRepository<Refunds, Long> {

}
