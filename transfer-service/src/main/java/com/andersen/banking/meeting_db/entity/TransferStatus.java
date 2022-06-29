package com.andersen.banking.meeting_db.entity;


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
@Table (name = "transaction_status")
public class TransferStatus extends BaseEntity{

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;
}
