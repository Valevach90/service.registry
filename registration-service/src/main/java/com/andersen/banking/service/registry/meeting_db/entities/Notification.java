package com.andersen.banking.service.registry.meeting_db.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {

    @Id
    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "code")
    private String code;

    @Column(name = "time", nullable = false)
    private Timestamp time;
}
