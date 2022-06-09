package com.andersen.banking.service.payment.meeting_impl.date;

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
  public boolean checkIfDateIsLaterThanToday(LocalDate date) {
    return date.isAfter(LocalDate.now());
  }
}
