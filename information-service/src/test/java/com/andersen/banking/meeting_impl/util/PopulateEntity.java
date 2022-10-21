package com.andersen.banking.meeting_impl.util;

import com.adelean.inject.resources.core.InjectResources;
import com.andersen.banking.meeting_api.dto.deposit.CurrencyDto;
import com.andersen.banking.meeting_api.dto.deposit.Deposit;
import com.andersen.banking.meeting_api.dto.gateway.Currency;
import com.andersen.banking.meeting_api.dto.gateway.UserBalance;
import com.andersen.banking.meeting_api.dto.payment.Account;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PopulateEntity {

    public final static UUID FIRST_ID = UUID.randomUUID();
    public final static UUID SECOND_ID = UUID.randomUUID();
    public final static UUID THIRD_ID = UUID.randomUUID();
    private final static Map<String, CurrencyDto> currencyMap = Map.of(
            "RUS", new CurrencyDto(FIRST_ID, "RUS"),
            "USD", new CurrencyDto(SECOND_ID, "USD"),
            "BEL", new CurrencyDto(THIRD_ID, "BEL")
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
    private final static byte[] HEADER;

    private final static byte[] PAYLOAD;

    static {
        HEADER = InjectResources.resource()
                .withPath("/files/", "header.json")
                .bytes();
        PAYLOAD = InjectResources.resource()
                .withPath("/files/", "payload.json")
                .bytes();
    }

    private PopulateEntity() {
    }

    public static List<Currency> getExpectedCurrencyList() {
        return expectedCurrencyList;
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

    public static UserBalance populateUser() {
        UserBalance user = new UserBalance();
        user.setId(FIRST_ID);
        user.setCurrency(expectedCurrencyList);
        return user;
    }


    public static String populateJwt() {

        return base64(HEADER) + "." + base64(PAYLOAD);
    }

    private static Account populateAccount(Currency currency) {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setOwnerId(FIRST_ID);
        account.setCurrency(currency.getName());
        account.setBalance(currency.getBalance());
        return account;
    }

    private static Deposit populateDeposit(Currency currency) {
        Deposit deposit = new Deposit();
        deposit.setId(UUID.randomUUID());
        deposit.setUserId(FIRST_ID);
        deposit.setAmount((long) currency.getBalance());
        deposit.setCurrency(currencyMap.get(currency.getName()));
        return deposit;
    }

    private static String base64(byte[] s) {
        return Base64.getEncoder().encodeToString(s);
    }
}
