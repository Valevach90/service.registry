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

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created_date")
    private Date createdDate;

    @CreatedBy
    @Column(nullable = false, name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "last_modified_date")
    private Date lastModifiedDate;

    @LastModifiedBy
    @Column(nullable = false, name = "last_modified_by")
    private String lastModifiedBy;

    @Column(nullable = false, name = "deleted")
    private boolean deleted = false;

}

