package com.meerim_task.demo.domain.request;

import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.domain.StatusType;
import com.meerim_task.demo.domain.UserBalance;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CancelPaymentTransactionRequest {
    private UserBalance userBalance;
    private ServiceProvider serviceProvider;
    private Integer amount;
    private LocalDateTime transactionTimestamp;
    private StatusType statusType = StatusType.CANCELED;
}
