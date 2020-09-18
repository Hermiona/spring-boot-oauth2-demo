package com.meerim_task.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_balance_id")
    private UserBalance userBalance;

    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    private ServiceProvider serviceProvider;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name="transaction_timestamp", nullable = false)
    private LocalDateTime transactionTimestamp;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status;
}

enum StatusType{
    PENDING,
    CANCELED,
    COMPLETED
}
