package com.meerim_task.demo.facade;

import com.meerim_task.demo.controller.dto.ServiceProviderTransactionsViewDto;
import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.domain.projection.ServiceProviderTransactionsView;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.mapper.ServiceProviderTransactionsViewMapper;
import com.meerim_task.demo.service.ServiceProviderService;
import com.meerim_task.demo.service.ServiceProviderTransactionsViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/* Main API need docs as well*/
public interface ServiceProviderTransactionsViewFacade {
    ServiceProviderTransactionsViewDto get(Long serviceProviderId) throws NotFoundException;
}

@RequiredArgsConstructor
@Service
class DefaultServiceProviderTransactionsViewFacade implements ServiceProviderTransactionsViewFacade {
    private final ServiceProviderTransactionsViewMapper viewMapper;
    private final ServiceProviderTransactionsViewService viewService;
    private final ServiceProviderService serviceProviderService;

    @Override
    public ServiceProviderTransactionsViewDto get(Long serviceProviderId) throws NotFoundException {
        ServiceProvider serviceProvider = serviceProviderService.getById(serviceProviderId);
        ServiceProviderTransactionsView view = viewService.get(serviceProvider);
        return viewMapper.toServiceProviderTransactionsViewDto(view);
    }
}
