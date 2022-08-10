package com.andersen.banking.gateway.meeting_impl.util;

import com.andersen.banking.gateway.meeting_api.dto.deposit.CurrencyDto;
import com.andersen.banking.gateway.meeting_api.dto.deposit.Deposit;
import com.andersen.banking.gateway.meeting_api.dto.gateway.Currency;
import com.andersen.banking.gateway.meeting_api.dto.gateway.User;
import com.andersen.banking.gateway.meeting_api.dto.payment.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PopulateEntity {

    private final static Random random = new Random();
    private final static Map<String, CurrencyDto> currencyMap = Map.of(
            "RUS", new CurrencyDto(1L, "RUS"),
            "USD", new CurrencyDto(2L, "USD"),
            "BEL", new CurrencyDto(3L, "BEL")
    );

    private final static List<Currency> currencyList = List.of(
            new Currency("RUS", 100.00),
            new Currency("USD", 200.00),
            new Currency("BEL", 300.00)
    );

    private final static List<Currency> expectedCurrencyList = List.of(
            new Currency("RUS", 200.00),
            new Currency("USD", 400.00),
            new Currency("BEL", 600.00)
    );

    public static List<Currency> getExpectedCurrencyList() {
        return expectedCurrencyList;
    }

    private PopulateEntity() {
    }

    public static List<Account> createListAccount() {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(populateAccount(currencyList.get(0)));
        accounts.add(populateAccount(currencyList.get(1)));
        accounts.add(populateAccount(currencyList.get(2)));
        return accounts;
    }

    public static List<Deposit> createListDeposit() {
        ArrayList<Deposit> deposits = new ArrayList<>();
        deposits.add(populateDeposit(currencyList.get(0)));
        deposits.add(populateDeposit(currencyList.get(1)));
        deposits.add(populateDeposit(currencyList.get(2)));
        return deposits;
    }

    public static User populateUser() {
        User user = new User();
        user.setId(1L);
        user.setCurrency(expectedCurrencyList);
        return user;
    }

    private static Account populateAccount(Currency currency) {
        Account account = new Account();
        account.setId(random.nextLong());
        account.setOwnerId(1L);
        account.setCurrency(currency.getName());
        account.setBalance(currency.getBalance());
        return account;
    }

    private static Deposit populateDeposit(Currency currency) {
        Deposit deposit = new Deposit();
        deposit.setId(random.nextLong());
        deposit.setUserId(1L);
        deposit.setAmount((long) currency.getBalance());
        deposit.setCurrency(currencyMap.get(currency.getName()));
        return deposit;
    }
}
