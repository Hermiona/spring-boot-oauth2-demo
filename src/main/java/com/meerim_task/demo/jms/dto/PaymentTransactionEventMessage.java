package com.meerim_task.demo.jms.dto;

import com.meerim_task.demo.domain.PaymentTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransactionEventMessage implements Serializable {
    private Long paymentTransactionId;
    private Long serviceProviderId;
}
