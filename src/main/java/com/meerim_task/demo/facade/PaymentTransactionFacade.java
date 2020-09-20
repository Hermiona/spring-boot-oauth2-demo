package com.meerim_task.demo.facade;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.domain.UserBalance;
import com.meerim_task.demo.domain.request.CancelPaymentTransactionRequest;
import com.meerim_task.demo.domain.request.CompletePaymentTransactionRequest;
import com.meerim_task.demo.domain.request.CreatePaymentTransactionRequest;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.facade.dto.CancelPaymentTransactionRequestDto;
import com.meerim_task.demo.facade.dto.CompletePaymentTransactionRequestDto;
import com.meerim_task.demo.facade.dto.CreatePaymentTransactionRequestDto;
import com.meerim_task.demo.facade.dto.PaymentTransactionDto;
import com.meerim_task.demo.mapper.PaymentTransactionMapper;
import com.meerim_task.demo.service.PaymentTransactionService;
import com.meerim_task.demo.service.ServiceProviderService;
import com.meerim_task.demo.service.UserBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public interface PaymentTransactionFacade {
    PaymentTransactionDto create(CreatePaymentTransactionRequestDto dtoRequest) throws NotFoundException;

    PaymentTransactionDto cancel(CancelPaymentTransactionRequestDto dtoRequest) throws NotFoundException;

    PaymentTransactionDto complete(CompletePaymentTransactionRequestDto dtoRequest) throws NotFoundException;
}

@RequiredArgsConstructor
@Service
class DefaultPaymentTransactionFacade implements PaymentTransactionFacade {
    private final PaymentTransactionService paymentTransactionService;
    private final UserBalanceService userBalanceService;
    private final ServiceProviderService serviceProviderService;
    private final PaymentTransactionMapper paymentTransactionMapper;
    private final Supplier<LocalDateTime> currentDateTimeProvider = LocalDateTime::now;

    @Transactional
    @Override
    public PaymentTransactionDto create(CreatePaymentTransactionRequestDto dtoRequest) throws NotFoundException {
        UserBalance userBalance = userBalanceService.findById(dtoRequest.getUserBalanceId());
        ServiceProvider serviceProvider = serviceProviderService.findById(dtoRequest.getServiceProviderId());
        LocalDateTime transactionTimestamp = currentDateTimeProvider.get();
        PaymentTransaction paymentTransaction = paymentTransactionService.create(new CreatePaymentTransactionRequest(userBalance, serviceProvider, dtoRequest.getAmount(), transactionTimestamp));
        return paymentTransactionMapper.toPaymentTransactionDto(paymentTransaction);
    }

    @Transactional
    @Override
    public PaymentTransactionDto cancel(CancelPaymentTransactionRequestDto dtoRequest) throws NotFoundException {
        UserBalance userBalance = userBalanceService.findById(dtoRequest.getUserBalanceId());
        ServiceProvider serviceProvider = serviceProviderService.findById(dtoRequest.getServiceProviderId());
        LocalDateTime transactionTimestamp = currentDateTimeProvider.get();
        PaymentTransaction paymentTransaction = paymentTransactionService.cancel(new CancelPaymentTransactionRequest(userBalance, serviceProvider, dtoRequest.getAmount(), transactionTimestamp));
        return paymentTransactionMapper.toPaymentTransactionDto(paymentTransaction);
    }

    @Transactional
    @Override
    public PaymentTransactionDto complete(CompletePaymentTransactionRequestDto dtoRequest) throws NotFoundException {
        UserBalance userBalance = userBalanceService.findById(dtoRequest.getUserBalanceId());
        ServiceProvider serviceProvider = serviceProviderService.findById(dtoRequest.getServiceProviderId());
        LocalDateTime transactionTimestamp = currentDateTimeProvider.get();
        PaymentTransaction paymentTransaction = paymentTransactionService.complete(new CompletePaymentTransactionRequest(userBalance, serviceProvider, dtoRequest.getAmount(), transactionTimestamp));
        return paymentTransactionMapper.toPaymentTransactionDto(paymentTransaction);
    }
}
