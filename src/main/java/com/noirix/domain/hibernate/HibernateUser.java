package com.noirix.domain.hibernate;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.noirix.domain.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
//@Cacheable("users") // будут сохранятсе юзеры в кэше по любым запросам
@EqualsAndHashCode(exclude = {
        "roles", "car"
})
@NamedQuery(name = "HibernateUser_findWithIdRestriction", query = "select h from HibernateUser h where h.id > :id")
public class HibernateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "surname", column = @Column(name = "surname")),
            @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date")),
    })
    private UserCredentials userCredentials;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "login", column = @Column(name = "login")),
            @AttributeOverride(name = "password", column = @Column(name = "password"))
    })
    private HibernateUserCredentials credentials;

    @Column
    private Float weight;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private HibernateCar car;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<HibernateRole> roles = Collections.emptySet();
}
