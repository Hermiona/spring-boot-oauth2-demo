package com.meerim_task.demo.domain.request;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.StatusType;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class CompletePaymentTransactionRequest {
     PaymentTransaction paymentTransaction;
     StatusType statusType = StatusType.COMPLETED;
}
