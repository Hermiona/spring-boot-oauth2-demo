package com.meerim_task.demo.repository;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
}
