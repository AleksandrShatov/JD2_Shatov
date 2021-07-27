package com.noirix.domain.hibernate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HibernateUserCredentials {

    private String login;

    private String password;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "login", column = @Column(name = "login")),
//            @AttributeOverride(name = "password", column = @Column(name = "password"))
//    })
//    private HibernateUserCredentials credentials;
}
