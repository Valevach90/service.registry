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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
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
    @Transactional(readOnly = true)
    public TransferResponseDto findById(UUID id) {
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
    public Transfer create(TransferRequestDto transferRequestDto) {
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
    public TransferStatusResponseDto getTransferStatus(UUID transferId) {

        return null;
    }


}
