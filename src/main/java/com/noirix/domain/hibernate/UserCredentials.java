package com.noirix.domain.hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentials {

    private String name;

    private String surname;

    private LocalDateTime birthDate;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "name", column = @Column(name = "name")),
//            @AttributeOverride(name = "surname", column = @Column(name = "surname")),
//            @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date")),
//    })
//    private UserCredentials userCredentials;
}
