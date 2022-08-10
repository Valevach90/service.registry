package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_db.repository.PaymentTypeRepository;
import com.andersen.banking.meeting_db.repository.TransferStatusRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.CurrencyMapper;
import com.andersen.banking.meeting_impl.mapper.PaymentTypeMapper;
import com.andersen.banking.meeting_impl.mapper.TransferStatusMapper;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyServiceImpl implements TransferMoneyService {

    private final CurrencyRepository currencyRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final TransferStatusRepository transferStatusRepository;

    private final CurrencyMapper currencyMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final TransferStatusMapper transferStatusMapper;

    @Override
    @Cacheable("currencies")
    public List<CurrencyResponseDto> getAllCurrencies() {
        log.debug("Get currencies");
        return currencyRepository.findAll()
                .stream().map(currencyMapper::currency2CurrencyResponseDto).toList();
    }

    @Override
    @Cacheable("paymentTypes")
    public List<PaymentTypeResponseDto> getAllPaymentTypes() {
        log.debug("Get paymentTypes");
        return paymentTypeRepository.findAll()
                .stream().map(paymentTypeMapper::paymentType2PaymentTypeResponseDto).toList();

    }

    @Override
    public TransferStatusResponseDto getTransferStatus(Long transferId) {
        log.debug("Get transferStatus by id : {}", transferId);

        TransferStatus transferStatus = transferStatusRepository.findById(transferId)
                .orElseThrow(() -> new NotFoundException(TransferStatus.class, transferId));
        
        return transferStatusMapper.transferStatus2TransferStatusResponseDto(transferStatus);
    }

    @Override
    public void createTransferForAccruedAmount(String message) {

        ArrayList<String> fromMessage = parsingString(message);

        Long userId = Long.parseLong(fromMessage.get(0));
        String currency = fromMessage.get(1);
        Long amount = Long.parseLong(fromMessage.get(2));

        Transfer transfer = new Transfer();

        //TODO: the task of filling in the transfer fields and saving the transfer in the database

    }

    private ArrayList<String> parsingString(String message) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] subStr = message.split("_");
        for (int i = 0; i < subStr.length; i++) {
            arrayList.add(subStr[i]);
        }
        return arrayList;
    }

}
