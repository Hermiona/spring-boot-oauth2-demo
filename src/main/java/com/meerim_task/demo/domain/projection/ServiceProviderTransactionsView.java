package com.meerim_task.demo.domain.projection;

import com.meerim_task.demo.domain.ServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class ServiceProviderTransactionsView {
    ServiceProvider serviceProvider;
    Long sum;
    Long count;
}
