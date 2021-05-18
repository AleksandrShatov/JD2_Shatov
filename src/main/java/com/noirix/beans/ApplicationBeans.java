package com.noirix.beans;

import com.noirix.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeans {

    @Bean
    public StringUtils getStringUtils() {
        return new StringUtils();
    }

}
