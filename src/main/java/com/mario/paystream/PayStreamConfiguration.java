package com.mario.paystream;

import com.mario.paystream.application.domain.model.Money;
import com.mario.paystream.application.domain.service.MoneyTransferProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PayStreamConfigurationProperties.class)
public class PayStreamConfiguration {


    /**
     * Adds a use-case-specific {@link MoneyTransferProperties} object to the application context. The properties
     * are read from the Spring-Boot-specific {@link PayStreamConfigurationProperties} object.
     */
    @Bean
    public MoneyTransferProperties moneyTransferProperties(PayStreamConfigurationProperties payStreamConfigurationProperties){
        return new MoneyTransferProperties(Money.of(payStreamConfigurationProperties.getTransferThreshold()));
    }
}
