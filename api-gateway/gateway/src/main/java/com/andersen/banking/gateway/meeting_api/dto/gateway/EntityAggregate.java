package com.andersen.banking.gateway.meeting_api.dto.gateway;

import com.andersen.banking.gateway.meeting_api.dto.deposit.Deposit;
import com.andersen.banking.gateway.meeting_api.dto.payment.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class EntityAggregate {

    List<Deposit> deposits;
    List<Account> accounts;
}
