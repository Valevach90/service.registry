package com.andersen.banking.service.registry.meeting_db.entities;

import java.util.UUID;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@ToString
public class User {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "patronymic")
    private String patronymic;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "phone")
    private String phone;

}
