package com.andersen.banking.meeting_db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "time_table")
public class TimeTable extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "week_day_from", nullable = false)
    private String dayFrom;

    @Column(name = "week_day_to")
    private String dayTo;

    @Column(name = "time_from", nullable = false)
    private String timeFrom;

    @Column(name = "time_to", nullable = false)
    private String timeTo;

}
