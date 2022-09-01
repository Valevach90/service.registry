package com.andersen.banking.service.transfer.meeting_impl.service;

import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_db.repository.TransferRepository;
import com.andersen.banking.meeting_db.repository.TransferStatusRepository;
import com.andersen.banking.meeting_impl.mapper.TransferMapper;
import com.andersen.banking.meeting_impl.mapper.TransferStatusMapperImpl;
import com.andersen.banking.meeting_impl.service.CurrencyService;
import com.andersen.banking.meeting_impl.service.PaymentTypeService;
import com.andersen.banking.meeting_impl.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = {TransferServiceImpl.class, TransferStatusMapperImpl.class})
public class TransferMoneyServiceImplTest {
    private static final UUID ID = new UUID(11, 22);

    private final static Optional<TransferStatus> transferStatusOptional = Optional.of(new TransferStatus());

    @MockBean
    TransferStatusRepository transferStatusRepository;
    @MockBean
    CurrencyService currencyService;
    @MockBean
    TransferMapper transferMapper;
    @MockBean
    TransferRepository transferRepository;
    @MockBean
    PaymentTypeService paymentTypeService;

    @SpyBean
    TransferStatusMapperImpl transferStatusMapper;
    @SpyBean
    TransferServiceImpl transferService;

    //Method getTransferStatusIsNotSupportedNow
    @Disabled
    void whenGetTransferStatus_andOk() {
        Mockito
                .when(transferStatusRepository.findById(ID))
                .thenReturn(transferStatusOptional);

        var result = Optional.of(transferStatusMapper
                .TransferStatusResponseDto2transferStatus(transferService.getTransferStatus(ID)));

        assertEquals(transferStatusOptional, result);
    }
}
