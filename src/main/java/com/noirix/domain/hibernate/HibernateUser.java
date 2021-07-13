package com.noirix.domain.hibernate;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.noirix.domain.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {
        "roles", "car"
})
public class HibernateUser {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private HibernateCar car;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<HibernateRoles> roles = Collections.emptySet();
}
