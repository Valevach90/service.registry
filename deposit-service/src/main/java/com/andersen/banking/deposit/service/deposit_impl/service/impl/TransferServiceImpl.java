package com.andersen.banking.deposit.service.deposit_impl.service.impl;
import com.andersen.banking.deposit.service.deposit_db.entities.Transfer;
import com.andersen.banking.deposit.service.deposit_db.repositories.TransferRepository;
import com.andersen.banking.deposit.service.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit.service.deposit_impl.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Override
    @Transactional
    public Transfer create(Transfer transfer) {
        log.info("Creating transfer: {}", transfer);

        transfer.setId(null);

        Transfer savedTransfer = transferRepository.save(transfer);

        log.info("Created transfer: {}", savedTransfer);
        return savedTransfer;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transfer> findById(Long id) {
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

        Transfer foundTransfer = transferRepository.findById(transfer.getId())
                .orElseThrow(() -> new NotFoundException(Transfer.class, transfer.getId()));

        transferRepository.save(transfer);

        log.info("Transfer: {} updated to version: {}", foundTransfer, transfer);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting transfer with id: {}", id);

        Transfer foundTransfer = transferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Transfer.class, id));

        transferRepository.deleteById(id);

        log.info("Deleted transfer: {}", foundTransfer);
    }
}
