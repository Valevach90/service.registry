package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
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
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Cacheable
    @Transactional(readOnly = true)
    public TransferResponseDto findById(UUID id) throws NotFoundException {
        log.debug("Finding transfer by id: {}", id);

        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Transfer.class, id));
        TransferResponseDto responseDto = transferMapper.transfer2transferResponseDto(transfer);

        log.debug("Transfer with id {} was successfully found", id);

        return responseDto;
    }


    /**
     * @param id
     * @param status
     */
    @Override
    @Transactional
    public void changeTransferStatus(UUID id, int status) {
        log.info("Changing status for transferLog : {} to {}", id, status);

        Transfer transfer = transferRepository.getById(id);
        transfer.setStatus(status);
        transferRepository.save(transfer);

        log.info("Return result of status for transferLog : {} to {}", id, status);


    }

    /**
     * @param transferRequestDto
     * @return
     */

    @Override
    @CachePut
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
    @Cacheable
    @Transactional(readOnly = true)
    public List<TransferResponseDto> findByUserId(Long userId, Pageable pageable) {

        log.info("Finding transfers for userId: {}", userId);

        Page<Transfer> transfers = transferRepository.findByUserId(userId, pageable);

        log.info("Found transfers : {}", transfers);

        return transfers.stream().map(transferMapper::transfer2transferResponseDto).collect(Collectors.toList());
    }


}
