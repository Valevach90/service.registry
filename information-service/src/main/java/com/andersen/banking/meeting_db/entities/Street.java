package com.andersen.banking.meeting_db.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "street")
public class Street extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "city_id")
    private City city;

    @Column(nullable = false, name = "street_name", length = 50)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "street",
            cascade = {CascadeType.PERSIST})
    private List<Atm> atms;

}
