package com.mario.paystream;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "paystream")
public class PayStreamConfigurationProperties {

    private long transferThreshold = Long.MAX_VALUE;
}
