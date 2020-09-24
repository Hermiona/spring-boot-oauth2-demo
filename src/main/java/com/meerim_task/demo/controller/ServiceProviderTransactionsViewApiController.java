package com.meerim_task.demo.controller;

import com.meerim_task.demo.controller.dto.ServiceProviderTransactionsViewDto;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.facade.ServiceProviderTransactionsViewFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service_providers_transactions")
public class ServiceProviderTransactionsViewApiController {
    private final ServiceProviderTransactionsViewFacade serviceProviderTransactionsViewFacade;

    @GetMapping("/{serviceProviderId}")
    public ServiceProviderTransactionsViewDto get(@PathVariable Long serviceProviderId) throws NotFoundException {
        return serviceProviderTransactionsViewFacade.get(serviceProviderId);
    }
}
