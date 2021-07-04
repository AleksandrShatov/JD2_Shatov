package com.noirix.domain.hibernate;


import com.noirix.domain.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class HibernateUser {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @Column
    private String login;

    @Column
    private Float weight;

    @Column
    private String password;
}
