package com.andersen.banking.meeting_impl.service.impl;
import com.andersen.banking.meeting_db.entities.Transfer;
import com.andersen.banking.meeting_db.repositories.TransferRepository;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Override
    @Transactional
    public Transfer create(Transfer transfer) {
        log.info("Creating transfer: {}", transfer);

        transfer.setTransferId(null);

        Transfer savedTransfer = transferRepository.save(transfer);

        log.info("Created transfer: {}", savedTransfer);
        return savedTransfer;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transfer> findById(UUID id) {
        log.info("Find transfer by id: {}", id);

        Optional<Transfer> transfer = transferRepository.findById(id);

        log.info("Found transfer by id: {}", transfer);
        return transfer;
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

        transferRepository.save(transfer);

        log.info("Transfer: {} updated to version: {}", foundTransfer, transfer);
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
