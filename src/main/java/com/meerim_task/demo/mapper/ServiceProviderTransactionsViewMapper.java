package com.meerim_task.demo.mapper;

import com.meerim_task.demo.controller.dto.ServiceProviderTransactionsViewDto;
import com.meerim_task.demo.domain.projection.ServiceProviderTransactionsView;
import org.springframework.stereotype.Service;

public interface ServiceProviderTransactionsViewMapper {
    ServiceProviderTransactionsViewDto toServiceProviderTransactionsViewDto(ServiceProviderTransactionsView view);
}

@Service
class DefaultServiceProviderTransactionsViewMapper implements ServiceProviderTransactionsViewMapper{
    @Override
    public ServiceProviderTransactionsViewDto toServiceProviderTransactionsViewDto(ServiceProviderTransactionsView view) {
        return new ServiceProviderTransactionsViewDto(view.getServiceProvider().getAccountDetails(), view.getServiceProvider().getName(), view.getSum(), view.getCount());
    }
}