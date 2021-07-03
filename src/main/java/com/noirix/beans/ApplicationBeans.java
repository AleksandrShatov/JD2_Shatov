package com.noirix.beans;

import com.noirix.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class ApplicationBeans {

    @Bean
    @Primary
    public StringUtils getStringUtils() {
        return new StringUtils();
    }

    @Bean
    public NoOpPasswordEncoder noOpPasswordEncoder() { // Исключительно в образовательных целоях
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
