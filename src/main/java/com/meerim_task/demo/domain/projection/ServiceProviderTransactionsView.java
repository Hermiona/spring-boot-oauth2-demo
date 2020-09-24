package com.meerim_task.demo.domain.projection;

import com.meerim_task.demo.domain.ServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@RequiredArgsConstructor
@Value
public class ServiceProviderTransactionsView implements Serializable {
    ServiceProvider serviceProvider;
    Long sum;
    Long count;
}
