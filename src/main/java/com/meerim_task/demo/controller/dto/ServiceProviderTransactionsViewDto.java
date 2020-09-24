package com.meerim_task.demo.controller.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class ServiceProviderTransactionsViewDto {
    String accountDetails;
    String name;
    Long sum;
    Long count;
}
