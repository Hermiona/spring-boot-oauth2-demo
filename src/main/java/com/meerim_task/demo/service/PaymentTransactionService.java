package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.StatusType;
import com.meerim_task.demo.domain.request.CancelPaymentTransactionRequest;
import com.meerim_task.demo.domain.request.CompletePaymentTransactionRequest;
import com.meerim_task.demo.domain.request.CreatePaymentTransactionRequest;
import com.meerim_task.demo.domain.request.FindPaymentTransactionRequest;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.repository.PaymentTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;


public interface PaymentTransactionService {
    PaymentTransaction create(CreatePaymentTransactionRequest request);

    PaymentTransaction cancel(CancelPaymentTransactionRequest request);

    PaymentTransaction complete(CompletePaymentTransactionRequest request);

    PaymentTransaction find(FindPaymentTransactionRequest request) throws NotFoundException;

    Collection<PaymentTransaction> getToBeCompletedTransactions(Duration duration);

    boolean canBeCanceled(PaymentTransaction paymentTransaction);
}

@RequiredArgsConstructor
@Service
class DefaultPaymentTransactionService implements PaymentTransactionService {
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final Supplier<LocalDateTime> currentDateTimeProvider = LocalDateTime::now;

    @Transactional
    @Override
    public PaymentTransaction create(CreatePaymentTransactionRequest request) {
        LocalDateTime transactionTimestamp = currentDateTimeProvider.get();
        return paymentTransactionRepository.save(new PaymentTransaction(null, request.getUserBalance(), request.getServiceProvider(), request.getAmount(), transactionTimestamp, request.getStatusType(), null));
    }

    @Transactional
    @Override
    public PaymentTransaction cancel(CancelPaymentTransactionRequest request) {
        LocalDateTime transactionTimestamp = currentDateTimeProvider.get();
        return paymentTransactionRepository.save(new PaymentTransaction(null, request.getPaymentTransaction().getUserBalance(), request.getPaymentTransaction().getServiceProvider(), request.getPaymentTransaction().getAmount(), transactionTimestamp, request.getStatusType(), request.getPaymentTransaction()));
    }

    @Transactional
    @Override
    public PaymentTransaction complete(CompletePaymentTransactionRequest request) {
        LocalDateTime transactionTimestamp = currentDateTimeProvider.get();
        return paymentTransactionRepository.save(new PaymentTransaction(null, request.getPaymentTransaction().getUserBalance(), request.getPaymentTransaction().getServiceProvider(), request.getPaymentTransaction().getAmount(), transactionTimestamp, request.getStatusType(), request.getPaymentTransaction()));
    }

    @Transactional(readOnly = true)
    @Override
    public PaymentTransaction find(FindPaymentTransactionRequest request) throws NotFoundException {
        return paymentTransactionRepository.findByIdAndUserBalanceAndServiceProviderAndStatus(
                request.getId(),
                request.getUserBalance(),
                request.getServiceProvider(),
                request.getStatusType()
        ).orElseThrow(() -> new NotFoundException(PaymentTransaction.class,
                Pair.of("id", request.getId()),
                Pair.of("userBalance", request.getUserBalance().getBalance()),
                Pair.of("serviceProvider", request.getServiceProvider().getId()),
                Pair.of("status", request.getStatusType())
        ));
    }

    @Override
    public Collection<PaymentTransaction> getToBeCompletedTransactions(Duration duration) {
        LocalDateTime pendingTransactionsLatestTime = currentDateTimeProvider.get().minus(duration);
        return paymentTransactionRepository.findToBeCompleted(StatusType.PENDING, pendingTransactionsLatestTime);
    }

    @Override
    public boolean canBeCanceled(PaymentTransaction paymentTransaction) {
        if (paymentTransaction.getStatus() != StatusType.PENDING)
            return false;
        Collection<PaymentTransaction> canceledOrCompletedChildren = paymentTransactionRepository.findByParentAndStatusIn(paymentTransaction, Set.of(StatusType.CANCELED, StatusType.COMPLETED));
        return canceledOrCompletedChildren.isEmpty();
    }
}