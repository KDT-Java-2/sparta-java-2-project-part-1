package com.moveuk.ecommerce.infra.balance;

import com.moveuk.ecommerce.domain.balance.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserBalanceRepository extends JpaRepository<UserBalance, Long> {
}
