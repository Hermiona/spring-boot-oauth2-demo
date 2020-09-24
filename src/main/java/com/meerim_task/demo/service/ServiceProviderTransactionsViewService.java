package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.domain.StatusType;
import com.meerim_task.demo.domain.projection.ServiceProviderTransactionsView;
import com.meerim_task.demo.exception.ConflictException;
import com.meerim_task.demo.repository.PaymentTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.function.Supplier;


public interface ServiceProviderTransactionsViewService {
    ServiceProviderTransactionsView get(ServiceProvider serviceProvider);

    ServiceProviderTransactionsView update(ServiceProviderTransactionsView serviceProviderTransactionsView, PaymentTransaction paymentTransaction) throws ConflictException;

}

@RequiredArgsConstructor
@Service
class DefaultServiceProviderTransactionsViewService implements ServiceProviderTransactionsViewService {
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final Supplier<LocalDateTime> currentDateTimeProvider = LocalDateTime::now;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "serviceProvidersTransactions", key = "#serviceProvider.id")
    public ServiceProviderTransactionsView get(ServiceProvider serviceProvider) {
        var minus1DayDatetime = currentDateTimeProvider.get().minusDays(1);
        return paymentTransactionRepository.getServiceProviderTransactionsView(StatusType.COMPLETED, minus1DayDatetime, serviceProvider);
    }

    @Override
    @CachePut(value = "serviceProvidersTransactions", key = "#paymentTransaction.serviceProvider.id")
    public ServiceProviderTransactionsView update(ServiceProviderTransactionsView serviceProviderTransactionsView, PaymentTransaction paymentTransaction) throws ConflictException {
        assert (serviceProviderTransactionsView.getServiceProvider() == paymentTransaction.getServiceProvider());

        Long amount = Long.valueOf(paymentTransaction.getAmount());
        switch (paymentTransaction.getStatus()) {
            case PENDING:
                amount = amount;
                break;
            case CANCELED:
                amount = -amount;
                break;
            default:
                throw new ConflictException("Cannot update service provider transactions view");
        }

        return new ServiceProviderTransactionsView(paymentTransaction.getServiceProvider(), serviceProviderTransactionsView.getSum() + amount, serviceProviderTransactionsView.getCount() + 1);
    }

}
