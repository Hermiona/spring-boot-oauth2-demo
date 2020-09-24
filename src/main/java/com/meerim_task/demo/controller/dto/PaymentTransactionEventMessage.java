package com.meerim_task.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransactionEventMessage implements Serializable {
    private PaymentTransactionEventType event;
}
