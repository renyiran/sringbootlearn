package com.anthony.springboot.configbean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by BG343891 on 2018/5/7.
 */
@Configuration
@ConfigurationProperties(prefix = "com.girl")
@PropertySource("classpath:properties/girl.properties")
@Data
public class GirlConfig {
    private String name;
    private String love;
}
