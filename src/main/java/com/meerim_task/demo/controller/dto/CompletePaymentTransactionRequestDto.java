package com.meerim_task.demo.controller.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class CompletePaymentTransactionRequestDto {
    Long userBalanceId;
    Long serviceProviderId;
    Long parentId;
}
