package com.andersen.banking.meeting_db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "file_info")
@NoArgsConstructor
public class FileInfo {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "date_of_creation", nullable = false)
    private Timestamp dateOfCreation;

    @Column(name = "date_of_update")
    private Timestamp dateOfUpdate;
}
