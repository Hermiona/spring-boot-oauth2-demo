package com.meerim_task.demo.repository;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.User;
import com.meerim_task.demo.domain.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
    Optional<UserBalance> findByIdAndUser(Long id, User user);
}
