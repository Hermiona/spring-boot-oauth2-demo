package com.meerim_task.demo.facade;

import com.meerim_task.demo.controller.dto.PaymentTransactionEventMessage;
import com.meerim_task.demo.domain.projection.ServiceProviderTransactionsView;
import com.meerim_task.demo.service.PaymentTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class ComputingServiceProvidersBalances {
    private final PaymentTransactionService paymentTransactionService;

    @JmsListener(destination = "payment.transaction")
    public void compute(PaymentTransactionEventMessage eventMessage) {
        Collection<ServiceProviderTransactionsView> serviceProviderTransactionsViews = paymentTransactionService.computeServiceProviderTransactions();
        //push serviceProviderTransactionsViews into cache
        System.out.println("Received" + eventMessage);
    }
}
