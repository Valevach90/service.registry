package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Deposit;
import java.util.List;

public interface ClosedDepositTransferService {

    List<Deposit> closingDeposits();

    void transferToAccount(List<Deposit> depositsForTransfer);

}
