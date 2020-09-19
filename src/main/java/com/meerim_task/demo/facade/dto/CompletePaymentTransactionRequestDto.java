package com.meerim_task.demo.facade.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class CompletePaymentTransactionRequestDto {
    Long userBalanceId;
    Long serviceProviderId;
    Integer amount;
}
