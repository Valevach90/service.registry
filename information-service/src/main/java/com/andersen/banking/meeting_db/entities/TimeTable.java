package com.andersen.banking.meeting_db.entities;

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
@Table(name = "time_table")
public class TimeTable extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "branch_id")
    private BankBranch bankBranch;

    @Column(nullable = false, name = "week_day_from")
    private String dayFrom;

    @Column(nullable = false, name = "week_day_to")
    private String dayTo;

    @Column(nullable = false, name = "time_from")
    private String timeFrom;

    @Column(nullable = false, name = "time_to")
    private String timeTo;
}
