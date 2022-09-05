package com.andersen.banking.meeting_db.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "registration_notifications")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrationNotification {

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "code")
    private String code;

    @Column(name = "time", nullable = false)
    private Timestamp time;

    @Column(name = "status", nullable = false)
    private String status;
}
