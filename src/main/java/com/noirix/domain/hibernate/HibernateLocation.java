package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"dealers"})
public class HibernateLocation {

    @Id
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<HibernateDealer> dealers = Collections.emptySet();
}
