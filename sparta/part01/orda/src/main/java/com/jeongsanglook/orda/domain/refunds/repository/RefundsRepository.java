package com.jeongsanglook.orda.domain.refunds.repository;

import com.jeongsanglook.orda.domain.refunds.entity.Refunds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundsRepository extends JpaRepository<Refunds, Long> {

}
