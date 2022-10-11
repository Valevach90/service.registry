package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Deposit;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ClosedDepositTransferService {

    void transferToAccount(List<Deposit> deposits);

}
