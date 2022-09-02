package com.andersen.banking.meeting_db.entities;

import com.andersen.banking.meeting_impl.config.CityNameConstraint;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
@EntityListeners(AuditingEntityListener.class)
public class City extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "country_id")
    private Country country;

    @Column(nullable = false, name = "city_name", length = 80)
    @CityNameConstraint
    @Size(min = 3)
    private String name;
}
