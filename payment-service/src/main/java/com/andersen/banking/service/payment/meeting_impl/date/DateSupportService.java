package com.andersen.banking.service.payment.meeting_impl.date;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
