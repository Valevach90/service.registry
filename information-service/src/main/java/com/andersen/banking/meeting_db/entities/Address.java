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
@Table(name = "address")
public class Address extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "zip")
    private String zip;

}
