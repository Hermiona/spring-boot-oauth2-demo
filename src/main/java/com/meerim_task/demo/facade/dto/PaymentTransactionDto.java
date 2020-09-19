package com.meerim_task.demo.facade.dto;

import com.meerim_task.demo.domain.StatusType;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Value
public class PaymentTransactionDto {
    Long id;
    UserBalanceSimplifiedDto userBalanceDto;
    ServiceProviderDto serviceProviderDto;
    Integer amount;
    LocalDateTime transactionTimestamp;
    StatusType status;
}
