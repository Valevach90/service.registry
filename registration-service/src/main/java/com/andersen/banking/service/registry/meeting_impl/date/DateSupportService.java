package com.andersen.banking.service.registry.meeting_impl.date;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class DateSupportService {

  /**
   * Returns true if the given date is later than today
   *
   * @param date
   * @return
   */
  public Boolean checkIfDateIsLaterThanToday(LocalDate date) {
    return date.isAfter(LocalDate.now());
  }

  /**
   * Returns true if the given dates are equal
   *
   * @param dateOne
   * @param dateTwo
   * @return
   */
  public Boolean checkIfTwoDatesAreEqual(LocalDate dateOne, LocalDate dateTwo) {
    return dateOne.equals(dateTwo);
  }
}
