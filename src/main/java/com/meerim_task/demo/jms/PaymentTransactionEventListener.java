package com.meerim_task.demo.jms;

import com.meerim_task.demo.jms.dto.PaymentTransactionEventMessage;
import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.domain.projection.ServiceProviderTransactionsView;
import com.meerim_task.demo.exception.ConflictException;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.service.PaymentTransactionService;
import com.meerim_task.demo.service.ServiceProviderService;
import com.meerim_task.demo.service.ServiceProviderTransactionsViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentTransactionEventListener {
    private final PaymentTransactionService paymentTransactionService;
    private final ServiceProviderService serviceProviderService;
    private final ServiceProviderTransactionsViewService serviceProviderTransactionsViewService;

    @JmsListener(destination = "payment.transaction")
    public void execute(PaymentTransactionEventMessage eventMessage) throws NotFoundException, ConflictException {
        ServiceProvider serviceProvider = serviceProviderService.getById(eventMessage.getServiceProviderId());
        PaymentTransaction paymentTransaction = paymentTransactionService.getByIdAndServiceProvider(eventMessage.getPaymentTransactionId(), serviceProvider);
        ServiceProviderTransactionsView serviceProviderTransactionsView = serviceProviderTransactionsViewService.get(serviceProvider);
        serviceProviderTransactionsViewService.update(serviceProviderTransactionsView, paymentTransaction);
    }

}
