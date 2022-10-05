package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_db.repository.RegularPaymentRepository;
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

        Card sourceCard = cardService.findById(regularPayment.getSourceCard().getId());
        regularPayment.setSourceCard(sourceCard);

        Card recipientCard = cardService.findById(regularPayment.getRecipientCard().getId());
        regularPayment.setRecipientCard(recipientCard);

        RegularPayment savedRegularPayment = regularPaymentRepository.save(regularPayment);

        log.info("Created regular payment: {}", savedRegularPayment);
        return savedRegularPayment;
    }


}
