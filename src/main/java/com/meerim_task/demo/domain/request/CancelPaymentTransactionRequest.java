package com.meerim_task.demo.domain.request;

import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.domain.StatusType;
import com.meerim_task.demo.domain.UserBalance;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Value
public class CancelPaymentTransactionRequest {
    UserBalance userBalance;
    ServiceProvider serviceProvider;
    Integer amount;
    LocalDateTime transactionTimestamp;
    StatusType statusType = StatusType.CANCELED;
}
