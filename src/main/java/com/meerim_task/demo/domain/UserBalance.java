package com.meerim_task.demo.domain;

import com.meerim_task.demo.exception.ConflictException;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user_balances")
public class UserBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "balance", nullable = false)
    private Integer balance;

    /* Атомарность операции ?, здесь или уровнем выше */
    public void withdraw(Integer amount) throws ConflictException {
        if (this.balance - amount < 0) {
            throw new ConflictException("Cannot execute a withdraw operation. Not enough balance");
        }
        this.balance -= amount;
    }

    /* Атомарность операции ?, здесь или уровнем выше*/
    public void deposit(Integer amount) {
        this.balance += amount;
    }
}
