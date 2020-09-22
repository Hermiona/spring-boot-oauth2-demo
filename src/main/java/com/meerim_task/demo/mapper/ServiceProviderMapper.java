package com.meerim_task.demo.mapper;

import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.controller.dto.ServiceProviderDto;
import org.springframework.stereotype.Service;

public interface ServiceProviderMapper {
    ServiceProviderDto toServiceProviderDto(ServiceProvider serviceProvider);
}

@Service
class DefaultServiceProvider implements ServiceProviderMapper{

    @Override
    public ServiceProviderDto toServiceProviderDto(ServiceProvider serviceProvider) {
        return new ServiceProviderDto(serviceProvider.getId(), serviceProvider.getAccountDetails(), serviceProvider.getName());
    }
}
