package com.meerim_task.demo.repository;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.domain.StatusType;
import com.meerim_task.demo.domain.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    Optional<PaymentTransaction> findByIdAndUserBalanceAndServiceProviderAndStatus(Long id, UserBalance userBalance, ServiceProvider serviceProvider, StatusType status);

    @Query(value = "select pt from PaymentTransaction pt " +
            "where pt.status = :status and pt.transactionTimestamp <= :timestamp " +
            "and not exists (select 1 from PaymentTransaction pt2 where " +
            "pt2.parent = pt)")
    Collection<PaymentTransaction> findToBeCompleted(@Param("status") StatusType status, @Param("timestamp") LocalDateTime timestamp);
}
