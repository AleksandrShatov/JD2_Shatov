
package com.noirix.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Configuration
@ConfigurationProperties("database")
public class DatabaseProperties {

    private String driver;

    private String url;

    private String login;

    private String password;
}