package com.meerim_task.demo.domain.request;

import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.domain.StatusType;
import com.meerim_task.demo.domain.UserBalance;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreatePaymentTransactionRequest {
    private final UserBalance userBalance;
    private final ServiceProvider serviceProvider;
    private final Integer amount;
    private final LocalDateTime transactionTimestamp;
    private StatusType statusType = StatusType.PENDING;
}
