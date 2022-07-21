package com.andersen.banking.deposit.service.deposit_db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "deposit_types")
@NoArgsConstructor
public class DepositType {

    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

/*    @OneToMany(mappedBy = "type", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<DepositProduct> products;*/
}
