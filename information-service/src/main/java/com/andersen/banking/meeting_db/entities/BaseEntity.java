package com.andersen.banking.meeting_db.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", nullable = false)
    private Date lastModifiedDate;

    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false)
    private String lastModifiedBy;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

}

