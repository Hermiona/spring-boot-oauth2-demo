package com.meerim_task.demo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties("payment-transaction")
public class PaymentTransactionProperty {
    public Duration completeTime;
}
