package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.TypeCard;
import com.andersen.banking.meeting_db.repository.TypeCardRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.TypeCardService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TypeCardServiceImpl implements TypeCardService {

    private final TypeCardRepository typeCardRepository;

    @Override
    @Transactional(readOnly = true)
    public TypeCard findById(UUID id) {
        log.info("Trying to find type card with id: {}", id);

        TypeCard foundTypeCard = typeCardRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(TypeCard.class, id));

        log.info("Founded type card: {}", foundTypeCard);
        return foundTypeCard;
    }

    @Override
    @Transactional(readOnly = true)
    public TypeCard findByPaymentSystemAndTypeName(String ps, String tn) {
        log.info("Trying to find type card by payment system and type name: [{}/{}]", ps, tn);

        TypeCard foundTypeCard = typeCardRepository.findByPaymentSystemAndTypeName(ps, tn)
                .orElseThrow(() -> new NotFoundException(TypeCard.class));

        log.info("Founded type card: {}", foundTypeCard);
        return foundTypeCard;
    }

    @Override
    @Transactional
    public TypeCard update(TypeCard typeCard) {
        log.info("Trying to update card type: {}", typeCard);

        TypeCard updatedTypeCard = findById(typeCard.getId());
        updatedTypeCard.setTypeName(typeCard.getTypeName());
        updatedTypeCard.setPaymentSystem(typeCard.getPaymentSystem());
        typeCardRepository.save(updatedTypeCard);

        log.info("Return update card type : {}", updatedTypeCard);
        return updatedTypeCard;
    }
}
