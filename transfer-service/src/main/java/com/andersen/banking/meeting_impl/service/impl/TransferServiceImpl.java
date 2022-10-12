package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.Currency;
import com.andersen.banking.meeting_db.entity.PaymentType;
import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_db.repository.TransferRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.TransferMapper;
import com.andersen.banking.meeting_impl.service.CurrencyService;
import com.andersen.banking.meeting_impl.service.PaymentTypeService;
import com.andersen.banking.meeting_impl.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@CacheConfig(cacheNames = {"transfer"})
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final CurrencyService currencyService;
    private final TransferMapper transferMapper;

    private final TransferRepository transferRepository;

    private final PaymentTypeService paymentTypeService;


    /**
     * @param id of the transfer
     * @return
     */


    @Override
    public Transfer findById(UUID id) throws NotFoundException {
        log.debug("Finding transfer by id: {}", id);

        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Transfer.class, id));

        log.debug("Transfer with id {} was successfully found", id);

        return transfer;
    }

    @Override
    public boolean isEqualStatus(UUID id, int status) throws NotFoundException {
        log.info("Check status in database for transfer {}", id);
        return transferRepository.existsByIdAndStatus(id, status);
    }


    /**
     * @param id
     * @param status
     */
    @Override
    @Transactional
    public void changeTransferStatus(UUID id, int status) {
        changeTransferStatus(id, status, null);
    }

    @Override
    @Transactional
    public void changeTransferStatus(UUID id, int status, String service) {

        log.info("Changing status for transferLog : {} to {}", id, status);

        Transfer transfer = transferRepository.getById(id);
        transfer.setStatus(status);
        if (service != null) {
            transfer.setService(service);
        }
        transferRepository.save(transfer);

        log.info("Return result of status for transferLog : {} to {}", id, status);
    }

    /**
     * @param transferRequestDto
     * @return
     */

    @Override
    @CacheEvict(value = "transfers", key = "#transferRequestDto.getUserId()")
    @Transactional
    public Transfer create(TransferRequestDto transferRequestDto) throws RuntimeException {
        log.info("Creating transfer: {}", transferRequestDto);

        PaymentType source = paymentTypeService.getPaymentTypeById(transferRequestDto.getSourcePaymentTypeId());
        PaymentType destination = paymentTypeService.getPaymentTypeById(transferRequestDto.getDestinationPaymentTypeId());

        Currency currency = currencyService.getCurrencyById(transferRequestDto.getCurrencyId());

        Transfer transfer = transferMapper.transferRequestDto2Transfer(transferRequestDto, source, destination, currency);
        Transfer savedTransfer = transferRepository.save(transfer);

        log.info("Created transfer: {}", savedTransfer);

        return savedTransfer;
    }

    /*
            Not supported now.
         */
    @Override
    @Transactional(readOnly = true)
    public TransferStatusResponseDto getTransferStatus(UUID transferId) {

        return null;
    }

    @Override
    @Cacheable(value = "transfers", key = "#userId")
    @Transactional(readOnly = true)
    public Page<Transfer> findByUserId(UUID userId, Pageable pageable) {

        log.info("Finding transfers for userId: {}", userId);

        Page<Transfer> transfers = transferRepository.findByUserId(userId, pageable);
        log.info("Info: {}" ,transferRepository.findByUserId(userId, pageable).stream().toList());

        log.info("Found transfers : {}", transfers);

        return transfers;
    }

    @Override
    public String getService(UUID id) {
        log.info("Get service for transfer");
        Transfer transfer = findById(id);
        return transfer.getService();
    }
}
