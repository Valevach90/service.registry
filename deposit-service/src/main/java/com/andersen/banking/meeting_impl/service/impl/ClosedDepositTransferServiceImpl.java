package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.entities.LinkedCard;
import com.andersen.banking.meeting_db.repositories.DepositRepository;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.feign.MoneyTransfer;
import com.andersen.banking.meeting_impl.feign.dto.TransferRequestDto;
import com.andersen.banking.meeting_impl.service.ClosedDepositTransferService;
import com.andersen.banking.meeting_impl.util.TransferMapContainer;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Slf4j
@Service
public class ClosedDepositTransferServiceImpl implements ClosedDepositTransferService {

    private final DepositRepository depositRepository;

    private final MoneyTransfer moneyTransfer;

    private final TransferMapContainer transferMapContainer;

    @Override
    @Transactional
    public void closingDeposits() {
        List<Deposit> deposits;
        do {
            Pageable page = Pageable.ofSize(1000);
            deposits = depositRepository.closingScheduler(page);
            deposits.forEach(deposit -> deposit.setIsActive(false));
            transferToAccount(deposits);
            log.info("deposits with the current closing date are closed");
            resetAmountAfterTransferToCard(deposits);
            depositRepository.saveAll(deposits);
        } while (!deposits.isEmpty());
    }

    public void transferToAccount(List<Deposit> deposits) {

        List<TransferRequestDto> list = deposits.stream()
                .map(this::createTransfer).toList();

        list.forEach(moneyTransfer::createTransfer);
    }

    private TransferRequestDto createTransfer(Deposit deposit) {

        return TransferRequestDto.builder()
                .amount(deposit.getAmount())
                .currencyId(transferMapContainer.getCurrencyMap().get("EURO"))
                .userId(deposit.getUserId())
                .sourcePaymentTypeId(transferMapContainer.getPaymentTypeMap().get("DEPOSIT"))
                .sourceNumber(deposit.getDepositNumber())
                .destinationPaymentTypeId(transferMapContainer.getPaymentTypeMap().get("CARD"))
                .destinationNumber(getCardNumber(deposit))
                .build();
    }

    private String getCardNumber(Deposit deposit) {
        LinkedCard card = deposit.getLinkedCards().stream().findFirst()
                .orElseThrow(() -> new NotFoundException(Deposit.class, deposit.getDepositNumber(), deposit.getUserId()));

        return card.getFirstTwelveNumbers() + card.getLastFourNumbers();
    }

    private void resetAmountAfterTransferToCard(List<Deposit> list) {
        list.forEach(x -> x.setAmount(0L));
    }
}
