package com.noirix.beans;

import com.noirix.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationBeans {

    @Bean
    @Primary
    public StringUtils getStringUtils() {
        return new StringUtils();
    }

}
