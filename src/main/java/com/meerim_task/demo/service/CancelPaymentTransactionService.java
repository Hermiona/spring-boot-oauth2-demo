package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.request.CancelPaymentTransactionRequest;
import com.meerim_task.demo.exception.ConflictException;
import com.meerim_task.demo.property.PaymentTransactionProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Supplier;

public interface CancelPaymentTransactionService {
    PaymentTransaction execute(PaymentTransaction paymentTransaction) throws ConflictException;
}

@RequiredArgsConstructor
@Service
class DefaultCancelPaymentTransactionService implements CancelPaymentTransactionService {
    private final PaymentTransactionService paymentTransactionService;
    private final Supplier<LocalDateTime> currentDateTimeProvider = LocalDateTime::now;
    private final PaymentTransactionProperty paymentTransactionProperty;

    @Override
    public PaymentTransaction execute(PaymentTransaction paymentTransaction) throws ConflictException {
        LocalDateTime currentDateTime = currentDateTimeProvider.get();
        Duration duration = Duration.between(currentDateTime, paymentTransaction.getTransactionTimestamp());
        if (duration.compareTo(paymentTransactionProperty.completeTime) >= 0) {
            throw new ConflictException("Payment transaction cannot be canceled. The available cancel time has expired");
        }
        return paymentTransactionService.cancel(new CancelPaymentTransactionRequest(paymentTransaction));
    }
}
