package com.andersen.banking.service.registry.meeting_db.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "time", nullable = false)
    private Timestamp time;
}
