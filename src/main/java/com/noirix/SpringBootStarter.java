package com.noirix;

import com.noirix.beans.ApplicationBeans;
import com.noirix.beans.GitSecretConfig;
import com.noirix.beans.SecurityConfig;
import com.noirix.beans.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.noirix")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@EnableSwagger2
@Import({
        ApplicationBeans.class,
        SwaggerConfig.class,
        SecurityConfig.class,
        GitSecretConfig.class})
public class SpringBootStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}