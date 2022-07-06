package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    List<TimeTable> getTimeTableByAddress_Id(Long addressId);

}
