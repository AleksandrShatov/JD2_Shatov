package com.noirix.beans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Data
public class SecurityConfig {

    @Value("${secretKey}")
    private String secretKey;
}
