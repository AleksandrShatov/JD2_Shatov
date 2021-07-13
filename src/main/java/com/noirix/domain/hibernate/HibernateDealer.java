package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "dealer")
@Data
@NoArgsConstructor
public class HibernateDealer {

    @Id
    private Long id;

    @Column
    private String name;

    @Column(name = "open_date")
    private LocalDateTime openDate;

    @Column(name = "location_description")
    private String locationDescription;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "open_hour")
    private Integer openHour;

    @Column(name = "close_hour")
    private Integer closeHour;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private HibernateLocation location;

}
