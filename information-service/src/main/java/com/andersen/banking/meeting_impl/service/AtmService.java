package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Atm;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AtmService {

    /**
     * Create new ATM.
     *
     * @param atm to create
     * @return ATM
     */
    Atm create(Atm atm);

    /**
     * Find a specific Atm by id.
     *
     * @param id (UUID) of Atm
     * @return Atm
     */
    Atm findById(UUID id);

    /**
     * Find all ATM.
     *
     * @param pageable page object
     * @return page of ATM
     */
    Page<Atm> findAll(Pageable pageable);

    /**
     * Find ATM by street id.
     *
     * @param streetId   - street id
     * @param pageable - page object
     * @return page of ATM on specific street
     */
    Page<Atm> findAllByStreetId(Long streetId, Pageable pageable);

    /**
     * Find ATM by street id.
     *
     * @param bankBranchId   - bank branch id
     * @param pageable - page object
     * @return page of ATM on specific branch
     */
    Page<Atm> findAllByBankBranchId(Long bankBranchId, Pageable pageable);

    /**
     * Update ATM.
     *
     * @param atm ATM to update
     * @return
     */
    Atm update(Atm atm);

    /**
     * Delete ATM by id.
     *
     * @param id id of ATM to delete
     */
    void deleteById(UUID id);

}
