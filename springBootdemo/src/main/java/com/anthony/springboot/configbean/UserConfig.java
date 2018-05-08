package com.anthony.springboot.configbean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by BG343891 on 2018/5/7.
 */

@ConfigurationProperties(prefix = "com.demo.user")
@Data
public class UserConfig {
    private String name;
    private String psw;
}
