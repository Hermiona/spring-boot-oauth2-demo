package com.meerim_task.demo.repository;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
}
