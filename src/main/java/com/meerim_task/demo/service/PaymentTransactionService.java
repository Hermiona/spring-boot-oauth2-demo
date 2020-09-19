package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.request.CancelPaymentTransactionRequest;
import com.meerim_task.demo.domain.request.CompletePaymentTransactionRequest;
import com.meerim_task.demo.domain.request.CreatePaymentTransactionRequest;
import com.meerim_task.demo.repository.PaymentTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface PaymentTransactionService {
    PaymentTransaction create(CreatePaymentTransactionRequest request);
    PaymentTransaction cancel(CancelPaymentTransactionRequest request);
    PaymentTransaction complete(CompletePaymentTransactionRequest request);
}

@RequiredArgsConstructor
@Service
class DefaultPaymentTransactionService implements PaymentTransactionService{
    private final PaymentTransactionRepository paymentTransactionRepository;

    @Transactional
    @Override
    public PaymentTransaction create(CreatePaymentTransactionRequest request) {
        return paymentTransactionRepository.save(new PaymentTransaction(null, request.getUserBalance(), request.getServiceProvider(), request.getAmount(), request.getTransactionTimestamp(), request.getStatusType()));
    }

    @Transactional
    @Override
    public PaymentTransaction cancel(CancelPaymentTransactionRequest request) {
        return paymentTransactionRepository.save(new PaymentTransaction(null, request.getUserBalance(), request.getServiceProvider(), request.getAmount(), request.getTransactionTimestamp(), request.getStatusType()));
    }

    @Transactional
    @Override
    public PaymentTransaction complete(CompletePaymentTransactionRequest request) {
        return paymentTransactionRepository.save(new PaymentTransaction(null, request.getUserBalance(), request.getServiceProvider(), request.getAmount(), request.getTransactionTimestamp(), request.getStatusType()));
    }
}