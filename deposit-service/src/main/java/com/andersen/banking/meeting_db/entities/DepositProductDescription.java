package com.andersen.banking.meeting_db.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "deposit_product_descriptions")
@NoArgsConstructor
public class DepositProductDescription {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deposit_product_id", referencedColumnName = "id")
    @ToString.Exclude
    private DepositProduct depositProduct;
}
