package com.andersen.banking.meeting_db.entities;

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
@Table(name = "city")
public class City extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "country_id")
    private Country country;

    @Column(nullable = false, name = "city_name")
    private String name;

}
