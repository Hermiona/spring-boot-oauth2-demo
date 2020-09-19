package com.meerim_task.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "service_providers")
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "account_details", unique = true, nullable = false)
    private String accountDetails;
}
