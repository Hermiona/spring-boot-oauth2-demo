package com.meerim_task.demo.facade.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class CancelPaymentTransactionRequestDto {
    Long userBalanceId;
    Long serviceProviderId;
    Integer amount;
}
