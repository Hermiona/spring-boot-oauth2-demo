package com.meerim_task.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_balances")
public class UserBalance {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance", nullable = false)
    private Integer balance;
}
