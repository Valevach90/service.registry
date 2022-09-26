package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Transfer;
import com.andersen.banking.meeting_db.repositories.TransferRepository;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.service.TransferService;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Override
    @Transactional
    public Transfer create(Transfer transfer) {
        log.info("Creating transfer: {}", transfer);

        transfer.setTime(new Timestamp(System.currentTimeMillis()));

        Transfer savedTransfer = transferRepository.save(transfer);

        log.info("Created transfer: {}", savedTransfer);
        return savedTransfer;
    }

    @Override
    public Transfer findById(UUID id) {
        log.info("Find transfer by id: {}", id);

        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Transfer.class, id));

        log.info("Found transfer by id: {}", transfer);
        return transfer;
    }

    @Override
    public boolean isExist(UUID id) {
        log.info("Check transfer exist with id {}", id);

        return transferRepository.existsById(id);
    }

    @Override
    public boolean isExistStatus(UUID id, int status) {
        log.info("Check transfer exist with id {}", id);

        return transferRepository.existsByTransferIdAndStatus(id, status);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Transfer> findAll(Pageable pageable) {
        log.info("Find all transfers for pageable: {}", pageable);

        Page<Transfer> pageOfTransfers = transferRepository.findAll(pageable);

        log.info("Found {} transfers", pageOfTransfers.getContent().size());
        return pageOfTransfers;
    }

    @Override
    @Transactional
    public void update(Transfer transfer) {
        log.info("Updating transfer: {}", transfer);

        Transfer foundTransfer = transferRepository.findById(transfer.getTransferId())
                .orElseThrow(() -> new NotFoundException(Transfer.class, transfer.getTransferId()));

        transfer.setTime(new Timestamp(System.currentTimeMillis()));
        transferRepository.save(transfer);

        log.info("Transfer: {} updated to version: {}", foundTransfer, transfer);
    }

    @Override
    public void changeTransferStatus(UUID id, int status) {
        log.info("Changing status for transferLog : {} to {}", id, status);
        Transfer transfer = findById(id);
        transfer.setStatus(status);
        transfer.setTime(new Timestamp(System.currentTimeMillis()));
        transferRepository.save(transfer);

        log.info("Return result of status for transferLog : {} to {}", id, status);

    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        log.info("Deleting transfer with id: {}", id);

        Transfer foundTransfer = transferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Transfer.class, id));

        transferRepository.deleteById(id);

        log.info("Deleted transfer: {}", foundTransfer);
    }
}
