package com.noirix.beans;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.noirix.util.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.util.concurrent.TimeUnit;

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

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("users");
        cacheManager.setCaffeine(cacheProperties());
        return cacheManager;
    }

    public Caffeine<Object, Object> cacheProperties() {
        return Caffeine.newBuilder()
                .initialCapacity(10)
                .maximumSize(50)
                .expireAfterAccess(20, TimeUnit.SECONDS)
                .weakKeys()
                .recordStats();
    }

}
