package com.andersen.banking.service.registry.meeting_db.entities;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * Passport entity.
 */
@Getter
@Setter
@Entity
@Table(name = "passport")
@ToString
@NoArgsConstructor
public class Passport {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "serial_number", nullable = false, length = 2)
    private String serialNumber;

    @Column(name = "passport_code", nullable = false, length = 7)
    private String passportCode;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "date_issue", nullable = false)
    private LocalDate dateIssue;

    @Column(name = "termination_date", nullable = false)
    private LocalDate terminationDate;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "department_issued", nullable = false)
    private String departmentIssued;

    @Column(name = "born_place")
    private String bornPlace;
}
