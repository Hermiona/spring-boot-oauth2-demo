package com.meerim_task.demo.domain;

import com.meerim_task.demo.exception.ConflictException;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_balances")
public class UserBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance", nullable = false)
    private Integer balance;

    public void withdraw(Integer amount) throws ConflictException {
        if (this.balance - amount < 0) {
            throw new ConflictException("Cannot execute a withdraw operation. Not enough balance");
        }
        this.balance -= amount;
    }

    public void deposit(Integer amount) {
        this.balance += amount;
    }
}
