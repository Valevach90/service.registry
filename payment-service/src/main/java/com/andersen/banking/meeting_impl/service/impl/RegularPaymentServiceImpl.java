package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.FrequencyDto;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_db.repository.RegularPaymentRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.CardService;
import com.andersen.banking.meeting_impl.service.RegularPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegularPaymentServiceImpl implements RegularPaymentService {

    private final RegularPaymentRepository regularPaymentRepository;
    private final CardService cardService;

    @Transactional
    @Override
    public RegularPayment create(RegularPayment regularPayment) {
        log.info("Creating regular payment: {}", regularPayment);

        regularPayment.setSourceCard(cardService.findById(regularPayment.getSourceCard().getId()));
        regularPayment.setRecipientCard(cardService.findById(regularPayment.getRecipientCard().getId()));
        regularPayment.setNextDate(regularPayment.getStartDate());

        RegularPayment savedRegularPayment = regularPaymentRepository.save(regularPayment);

        log.info("Created regular payment: {}", savedRegularPayment);
        return savedRegularPayment;
    }

    @Transactional
    public RegularPayment update(RegularPayment regularPaymentToUpdate) {
        log.info("Updating regular payment: {}", regularPaymentToUpdate);

        regularPaymentRepository.findById(regularPaymentToUpdate.getId())
                        .orElseThrow(() -> new NotFoundException(RegularPayment.class, regularPaymentToUpdate.getId()));

        RegularPayment updatedRegularPayment = regularPaymentRepository.save(regularPaymentToUpdate);

        log.info("Updated regular payment: {}", updatedRegularPayment);
        return updatedRegularPayment;
    }

    private void setUpNextDate(RegularPayment regularPayment) {
        FrequencyDto frequencyDto = parseFrequency(regularPayment.getFrequency());

        regularPayment.setNextDate(regularPayment.getNextDate()
                .plusYears(frequencyDto.getYears())
                .plusMonths(frequencyDto.getMounts())
                .plusMonths(frequencyDto.getWeeks())
                .plusMonths(frequencyDto.getDays())
        );
    }

    private FrequencyDto parseFrequency(String frequency) {
        String[] frequencies = frequency.split("_");
        Long years = Long.parseLong(frequencies[0].substring(0, frequencies[0].length() -1));
        Long mounts = Long.parseLong(frequencies[1].substring(0, frequencies[1].length() -1));
        Long weeks = Long.parseLong(frequencies[2].substring(0, frequencies[2].length() -1));
        Long days = Long.parseLong(frequencies[3].substring(0, frequencies[3].length() -1));

        return new FrequencyDto(years, mounts, weeks, days);
    }


}
