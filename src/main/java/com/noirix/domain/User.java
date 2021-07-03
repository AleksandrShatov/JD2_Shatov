package com.noirix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private Long id;
    private String name;
    private String surname;
    private Date birthDate = new Date(System.currentTimeMillis());
    private String login;
    private Float weight;
    private String password;

    public User(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
