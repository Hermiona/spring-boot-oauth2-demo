package com.meerim_task.demo.controller.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/*
    Из требований отсуствует реквизит провайдера (поставщика) услуг
 */
@Value
@RequiredArgsConstructor
public class CreatePaymentTransactionRequestDto {
    @NotNull
    Long userBalanceId;
    @NotNull
    Long serviceProviderId;
    @NotNull
    @Positive(message = "Amount must be positive > 0")
    Integer amount;
}
