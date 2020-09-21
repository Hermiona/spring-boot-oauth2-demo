package com.meerim_task.demo.domain.request;

import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.domain.StatusType;
import com.meerim_task.demo.domain.UserBalance;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class FindPaymentTransactionRequest {
    Long id;
    UserBalance userBalance;
    ServiceProvider serviceProvider;
    StatusType statusType;
}
