package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.ServiceProvider;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.repository.ServiceProviderRepository;
import jdk.jshell.spi.SPIResolutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

public interface ServiceProviderService {
    ServiceProvider findById(Long id) throws NotFoundException;
}

@RequiredArgsConstructor
@Service
class DefaultServiceProviderService implements ServiceProviderService {
    private final ServiceProviderRepository serviceProviderRepository;

    @Override
    public ServiceProvider findById(Long id) throws NotFoundException {
        return serviceProviderRepository.findById(id).orElseThrow(() -> new NotFoundException(ServiceProvider.class, Pair.of("id", id)));
    }
}
