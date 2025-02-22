package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Atm;
import com.andersen.banking.meeting_db.entities.BankBranch;
import com.andersen.banking.meeting_db.entities.Street;
import com.andersen.banking.meeting_db.repositories.AtmRepository;
import com.andersen.banking.meeting_db.repositories.BankBranchRepository;
import com.andersen.banking.meeting_db.repositories.StreetRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.AtmService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtmServiceImpl implements AtmService {

    private final StreetRepository streetRepository;

    private final BankBranchRepository bankBranchRepository;

    private final AtmRepository atmRepository;

    @Override
    public Atm create(Atm atm) {
        log.info("Create ATM: {}", atm);

        atm.setBankBranch(bankBranchRepository.getById(atm.getBankBranch().getId()));
        atm.setStreet(streetRepository.getById(atm.getStreet().getId()));
        Atm savedAtm = atmRepository.save(atm);

        log.info("Created ATM: {}", savedAtm);
        return savedAtm;
    }

    @Override
    public Atm findById(UUID id) {
        log.info("Search ATM with id: {}", id);

        Atm atm = atmRepository.findById(id).orElseThrow(
                () -> new NotFoundException(Atm.class, "id", id.toString())
        );

        log.info("Find deposit with id: {}", id);
        return atm;
    }

    @Override
    public Page<Atm> findAll(Pageable pageable) {
        log.info("Search all ATM for pageable: {}", pageable);

        Page<Atm> pageOfAtm = atmRepository.findAll(pageable);

        log.info("Found {} ATM", pageOfAtm.getContent().size());
        return pageOfAtm;
    }

    @Override
    public Page<Atm> findAllByStreetId(Long streetId, Pageable pageable) {
        log.debug("Find all ATM on the streetId {} and pageable: {}", streetId, pageable);

        if (!streetRepository.existsById(streetId)) {
            throw new NotFoundException(Street.class, "id", streetId.toString());
        }

        Page<Atm> pageOfAtm = atmRepository.findAllByStreetId(streetId, pageable);

        log.info("Found {} ATM on the streetId {}", pageOfAtm.getContent().size(), streetId);
        return pageOfAtm;
    }

    @Override
    public Page<Atm> findAllByBankBranchId(Long bankBranchId, Pageable pageable) {
        log.info("Find all ATM on the bank branch with id {} and pageable: {}", bankBranchId, pageable);

        if(!bankBranchRepository.existsById(bankBranchId)) {
            throw new NotFoundException(BankBranch.class, "id", bankBranchId.toString());
        }

        Page<Atm> pageOfAtm = atmRepository.findAllByBankBranchId(bankBranchId, pageable);

        log.info("Found {} ATM on the bank branch wit id {}", pageOfAtm.getContent().size(), bankBranchId);
        return pageOfAtm;
    }

    @Override
    public Atm update(Atm atm) {
        log.info("Updating ATM {}", atm);

        atm.setBankBranch(bankBranchRepository.getById(atm.getBankBranch().getId()));
        atm.setStreet(streetRepository.getById(atm.getStreet().getId()));
        Atm updatedAtm = atmRepository.save(atm);

        log.info("Updated ATM: {}", updatedAtm);
        return updatedAtm;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting ATM with id: {}", id);

        atmRepository.deleteById(id);

        log.info("ATM with id: {} deleted successfully", id);
    }
}
