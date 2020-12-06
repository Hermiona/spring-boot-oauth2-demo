package com.meerim_task.demo.facade;

import com.meerim_task.demo.controller.dto.*;
import com.meerim_task.demo.domain.*;
import com.meerim_task.demo.domain.request.CompletePaymentTransactionRequest;
import com.meerim_task.demo.domain.request.CreatePaymentTransactionRequest;
import com.meerim_task.demo.domain.request.FindPaymentTransactionRequest;
import com.meerim_task.demo.exception.ConflictException;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.jms.dto.PaymentTransactionEventMessage;
import com.meerim_task.demo.mapper.PaymentTransactionMapper;
import com.meerim_task.demo.property.PaymentTransactionProperty;
import com.meerim_task.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import java.util.Collection;

/* Важные интерфейсы должны иметь документацию на все методы */
public interface PaymentTransactionFacade {
    /* Почему объект Long а не примитив, type casting issue может уйти корнями на другие методы */
    PaymentTransactionDto create(Long userId, CreatePaymentTransactionRequestDto dtoRequest) throws NotFoundException, ConflictException;

    /* Почему объект Long а не примитив, type casting issue может уйти корнями на другие методы */
    PaymentTransactionDto cancel(Long userId, Long parentId, CancelPaymentTransactionRequestDto dtoRequest) throws NotFoundException, ConflictException;

}

@RequiredArgsConstructor
@Service
class DefaultPaymentTransactionFacade implements PaymentTransactionFacade {
    private final UserService userService;
    private final PaymentTransactionService paymentTransactionService;
    private final UserBalanceService userBalanceService;
    private final ServiceProviderService serviceProviderService;
    private final PaymentTransactionMapper paymentTransactionMapper;
    private final CancelPaymentTransactionService cancelPaymentTransactionService;
    private final PaymentTransactionProperty paymentTransactionProperty;
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final Queue queue;

    @Transactional
    @Override
    public PaymentTransactionDto create(Long userId, CreatePaymentTransactionRequestDto dtoRequest) throws NotFoundException, ConflictException {
        User user = userService.getById(userId);
        UserBalance userBalance = userBalanceService.getByIdAndUser(dtoRequest.getUserBalanceId(), user);
        ServiceProvider serviceProvider = serviceProviderService.getById(dtoRequest.getServiceProviderId());
        PaymentTransaction paymentTransaction = paymentTransactionService.create(new CreatePaymentTransactionRequest(userBalance, serviceProvider, dtoRequest.getAmount()));
        userBalanceService.withdraw(userBalance, dtoRequest.getAmount());
        jmsMessagingTemplate.convertAndSend(queue, new PaymentTransactionEventMessage(paymentTransaction.getId(), serviceProvider.getId()));
        return paymentTransactionMapper.toPaymentTransactionDto(paymentTransaction);
    }

    @Transactional
    @Override
    public PaymentTransactionDto cancel(Long userId, Long parentId, CancelPaymentTransactionRequestDto dtoRequest) throws NotFoundException, ConflictException {
        User user = userService.getById(userId);
        UserBalance userBalance = userBalanceService.getByIdAndUser(dtoRequest.getUserBalanceId(), user);
        ServiceProvider serviceProvider = serviceProviderService.getById(dtoRequest.getServiceProviderId());
        PaymentTransaction paymentTransaction = paymentTransactionService.find(new FindPaymentTransactionRequest(
                parentId,
                userBalance,
                serviceProvider,
                StatusType.PENDING
        ));
        /*
            Почему execute на cancel ? Должен был быть один обработчик всех транзакций., который может делать все операции в зависимости от вида
            То есть execute не только на cancel но и на проводку, отмену, и чек статуса
         */
        PaymentTransaction canceledPaymentTransaction = cancelPaymentTransactionService.execute(paymentTransaction);
        jmsMessagingTemplate.convertAndSend(queue, new PaymentTransactionEventMessage(paymentTransaction.getId(), serviceProvider.getId()));
        return paymentTransactionMapper.toPaymentTransactionDto(canceledPaymentTransaction);
    }

    @Scheduled(cron = "${payment-transaction.cron}")
    @Transactional
    public void completeTransactions() {
        Collection<PaymentTransaction> paymentTransactions = paymentTransactionService.getToBeCompletedTransactions(paymentTransactionProperty.getCompleteTime());
        paymentTransactions.stream().forEach(
                paymentTransaction -> paymentTransactionService.complete(new CompletePaymentTransactionRequest(paymentTransaction)));
    }
}
