package com.andersen.banking.meeting_impl.util;

import com.andersen.banking.meeting_api.dto.FrequencyDto;
import com.andersen.banking.meeting_db.entities.RegularPayment;

public class RegularPaymentUtil {

    public static void setUpNextDate(RegularPayment regularPayment) {
        FrequencyDto frequencyDto = parseFrequency(regularPayment.getFrequency());

        regularPayment.setNextDate(regularPayment.getNextDate()
                .plusYears(frequencyDto.getYears())
                .plusMonths(frequencyDto.getMounts())
                .plusWeeks(frequencyDto.getWeeks())
                .plusDays(frequencyDto.getDays())
        );
    }

    public static void backUpNextDate(RegularPayment regularPayment) {
        FrequencyDto frequencyDto = parseFrequency(regularPayment.getFrequency());

        regularPayment.setNextDate(regularPayment.getNextDate()
                .minusYears(frequencyDto.getYears())
                .minusMonths(frequencyDto.getMounts())
                .minusWeeks(frequencyDto.getWeeks())
                .minusDays(frequencyDto.getDays())
        );
    }

    private static FrequencyDto parseFrequency(String frequency) {
        String[] frequencies = frequency.split("_");
        Long years = Long.parseLong(frequencies[0].substring(0, frequencies[0].length() -1));
        Long mounts = Long.parseLong(frequencies[1].substring(0, frequencies[1].length() -1));
        Long weeks = Long.parseLong(frequencies[2].substring(0, frequencies[2].length() -1));
        Long days = Long.parseLong(frequencies[3].substring(0, frequencies[3].length() -1));

        return new FrequencyDto(years, mounts, weeks, days);
    }
}
