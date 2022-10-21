package com.andersen.banking.meeting_api.dto.gateway;

import com.andersen.banking.meeting_api.dto.deposit.Deposit;
import com.andersen.banking.meeting_api.dto.payment.Account;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class EntityAggregate {

    List<Deposit> deposits;
    List<Account> accounts;
}
