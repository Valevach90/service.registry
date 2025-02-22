package com.andersen.banking.service.registry.meeting_db.entities;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "address")
@NoArgsConstructor
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private UUID userId;

    @Column(name = "zip_code")
    private Integer zipCode;

    @Column(nullable = false, name = "country", length = 30)
    private String country;

    @Column(name = "region", length = 45)
    private String region;

    @Column(name = "location", length = 45)
    private String location;

    @Column(name = "city", length = 45)
    private String city;

    @Column(name = "street", length = 45)
    private String street;

    @Column(nullable = false, name = "house", length = 5)
    private String house;

    @Column(name = "building", length = 5)
    private String building;

    @Column(name = "flat", length = 5)
    private String flat;
}
