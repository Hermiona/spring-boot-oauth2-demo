package com.meerim_task.demo.mapper;

import com.meerim_task.demo.domain.PaymentTransaction;
import com.meerim_task.demo.controller.dto.PaymentTransactionDto;
import com.meerim_task.demo.controller.dto.ServiceProviderDto;
import com.meerim_task.demo.controller.dto.UserBalanceSimplifiedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface PaymentTransactionMapper {
    PaymentTransactionDto toPaymentTransactionDto(PaymentTransaction paymentTransaction);
}

@RequiredArgsConstructor
@Service
class DefaultPaymentTransactionMapper implements PaymentTransactionMapper{
    private final UserBalanceMapper userBalanceMapper;
    private final ServiceProviderMapper serviceProviderMapper;

    @Override
    public PaymentTransactionDto toPaymentTransactionDto(PaymentTransaction paymentTransaction) {
        UserBalanceSimplifiedDto userBalanceDto = userBalanceMapper.toUserBalanceSimplifiedDto(paymentTransaction.getUserBalance());
        ServiceProviderDto serviceProviderDto = serviceProviderMapper.toServiceProviderDto(paymentTransaction.getServiceProvider());
        return new PaymentTransactionDto(paymentTransaction.getId(), userBalanceDto, serviceProviderDto, paymentTransaction.getAmount(), paymentTransaction.getTransactionTimestamp(), paymentTransaction.getStatus());
    }
}
