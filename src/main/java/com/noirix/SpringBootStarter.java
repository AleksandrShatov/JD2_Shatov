package com.noirix;

import com.noirix.beans.*;
import com.noirix.security.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Аннотации для автоконфигурации
@SpringBootApplication(scanBasePackages = "com.noirix")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@EnableTransactionManagement
@EnableCaching
@EnableSwagger2
@Import({
        ApplicationBeans.class,
        SwaggerConfig.class,
        PersistenceBeanConfiguration.class,
        WebSecurityConfiguration.class,
        SecurityConfig.class,
        GitSecretConfig.class})
public class SpringBootStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}