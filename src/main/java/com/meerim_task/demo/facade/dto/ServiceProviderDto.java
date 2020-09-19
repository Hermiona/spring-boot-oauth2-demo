package com.meerim_task.demo.facade.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class ServiceProviderDto {
    Long id;
    String accountDetails;
    String name;
}
