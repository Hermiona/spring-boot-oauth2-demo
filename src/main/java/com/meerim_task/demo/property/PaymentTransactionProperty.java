package com.meerim_task.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Component
@ConfigurationProperties("payment-transaction")
@Data
public class PaymentTransactionProperty {
    @NotNull
    private Duration completeTime;

    @PostConstruct
    public void init(){
        System.out.println("CONFIG :: "+this.completeTime);
    }
}
