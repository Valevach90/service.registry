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
    @JoinColumn(nullable = false, name = "city_id")
    private City city;

    @Column(nullable = false, name = "street_name")
    private String streetName;

    @Column(nullable = false, name = "building_number")
    private String buildingNumber;

}
